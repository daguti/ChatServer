/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver.utils;

import chatserver.charts.DialPlotChart;
import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.swing.JLabel;

/**
 *
 * @author ESa10969
 */
public class CPUsageThread implements Runnable {

    private Thread th;
    private DialPlotChart cpuChart;
    private DialPlotChart ramChart;
    private JLabel usedMem;
    private JLabel availableMem;
            
    public CPUsageThread(DialPlotChart cpuChart, DialPlotChart ramChart, JLabel usedMem,
                         JLabel availableMem) {
        this.cpuChart     = cpuChart;
        this.ramChart     = ramChart;
        this.usedMem      = usedMem;
        this.availableMem = availableMem;
    }
    
    public void start() {
        th = new Thread(this);
        th.start();
    }
    
    public void stop() {
        th = null;
    }
    
    @Override
    public void run() {
        
        while(th != null) {
            try {
                cpuChart.setChartValue((int) getProcessCpuLoad());
                double free  = new Long(Runtime.getRuntime().freeMemory()/ 1000000).doubleValue();
                double used  = new Long(Runtime.getRuntime().totalMemory() / 1000000).doubleValue();
                double total = new Long(Runtime.getRuntime().maxMemory() / 1000000).doubleValue();
                used = used - free;
                free = total - used;
                
                usedMem.setText(Double.toString(used));
                availableMem.setText(Double.toString(total));
                ramChart.setChartValue((int) ((used/total) * 100));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CPUsageThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static double getProcessCpuLoad() {
        //Varaible definition
        MBeanServer mbs;
        ObjectName name;
        AttributeList list;
        Double value = 0.0;
        Attribute att;
        
        try {
            mbs  = ManagementFactory.getPlatformMBeanServer();
            name = ObjectName.getInstance("java.lang:type=OperatingSystem");
            list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });
            if (list.isEmpty()) return 0.0;

            att    = (Attribute)list.get(0);
            value  = (Double)att.getValue();
            // usually takes a couple of seconds before we get real values
            if (value == -1.0) return 0.0;
            //value = ((int)(value * 1000) / 10.0);
            value = value*1000;
        } catch(Exception ex) {
          Logger.getLogger(CPUsageThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        // returns a percentage value with 1 decimal point precision
        return value;
    }
}
