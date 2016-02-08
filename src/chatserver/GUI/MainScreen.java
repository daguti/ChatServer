/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver.GUI;

import chat.remote.interfaces.ClientRemoteItfz;
import chatserver.Server;
import chatserver.StaticData;
import chatserver.charts.DialPlotChart;
import chatserver.utils.CPUsageThread;
import java.awt.BorderLayout;
import java.awt.Color;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author ESa10969
 */
public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        addChart();
        StaticData.mainScr = this;
    }

    private void addChart() {
        DialPlotChart cpuChart = new DialPlotChart();
        DialPlotChart ramChart = new DialPlotChart();
        ChartPanel cpuChartPanel = cpuChart.getChart("CPU Usage");
        ChartPanel ramChartPanel = ramChart.getChart("RAM Usage");
        
        cpuPanel.setLayout(new BorderLayout(0, 0));
        cpuChartPanel.setVisible(true);
        cpuPanel.add(cpuChartPanel, BorderLayout.WEST);
        cpuPanel.add(ramChartPanel, BorderLayout.EAST);
        
        CPUsageThread th = new CPUsageThread(cpuChart, ramChart, usedMem, availableMem);
        th.start();
    }
    
    
    public void updateConnectionsTable(HashMap<String, ClientRemoteItfz> userConMap) {
        conNum.setText(Integer.toString(userConMap.size()));
        DefaultTableModel model = (DefaultTableModel) conTable.getModel();
        
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }
        for(String userName : userConMap.keySet()) {
            model.addRow(new Object[]{userName});
        }
        conTable.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cpuPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usedMem = new javax.swing.JLabel();
        availableMem = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        conTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        conNum = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        stopButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        disconnectBut = new javax.swing.JButton();
        discAllBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ChatServer");

        javax.swing.GroupLayout cpuPanelLayout = new javax.swing.GroupLayout(cpuPanel);
        cpuPanel.setLayout(cpuPanelLayout);
        cpuPanelLayout.setHorizontalGroup(
            cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cpuPanelLayout.setVerticalGroup(
            cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel2.setText("Used:");

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel3.setText("Available:");

        usedMem.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        usedMem.setText("0");

        availableMem.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        availableMem.setText("0");

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel4.setText("Mb");

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel5.setText("Mb");

        conTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserName"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(conTable);

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel6.setText("Connections:");

        conNum.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        conNum.setText("0");

        ipField.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        ipField.setText("localhost");

        jLabel7.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel7.setText("IP:");

        portField.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        portField.setText("1099");

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel8.setText("PORT:");

        stopButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopButtonMouseClicked(evt);
            }
        });

        startButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        startButton.setText("Start");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });

        disconnectBut.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        disconnectBut.setText("Disconnect");
        disconnectBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disconnectButMouseClicked(evt);
            }
        });

        discAllBut.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        discAllBut.setText("Disconnect All");
        discAllBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                discAllButMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cpuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(conNum, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(availableMem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(usedMem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(disconnectBut)
                            .addComponent(discAllBut)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(startButton)
                                .addGap(31, 31, 31)
                                .addComponent(stopButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cpuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usedMem, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(conNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(availableMem)
                            .addComponent(jLabel5))
                        .addGap(51, 51, 51)
                        .addComponent(disconnectBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(discAllBut))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stopButton)
                    .addComponent(startButton))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
        //Variable definition
        String ip = ipField.getText();
        String port = portField.getText();
        
        if(ip == null || ip.equals("") || port == null || port.equals("")) {
            JOptionPane.showMessageDialog(this, "IP and PORT must have a value", "An error message",
                                      JOptionPane.ERROR_MESSAGE);
            ipField.setBackground(Color.red);
            portField.setBackground(Color.red);
        } else {
            ipField.setEditable(false);
            portField.setEditable(false);
            Server.StartServer(ipField.getText(), Integer.valueOf(port));
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
    }//GEN-LAST:event_startButtonMouseClicked

    private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
        Server.StopServer();
        ipField.setEditable(true);
        portField.setEditable(true);
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }//GEN-LAST:event_stopButtonMouseClicked

    private void disconnectButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectButMouseClicked
        int selRow = conTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) conTable.getModel();
        String userName = (String) model.getValueAt(selRow, 0);
        
        try {
            StaticData.userConMap.get(userName).serverDisconnect();
            StaticData.userConMap.remove(userName);
            conNum.setText(Integer.toString(StaticData.userConMap.size()));
            model.removeRow(selRow);
            conTable.setModel(model);
        } catch(RemoteException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_disconnectButMouseClicked

    private void discAllButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discAllButMouseClicked
        //Variable definition
        DefaultTableModel model = (DefaultTableModel) conTable.getModel();
        String userName;
        
        try {
            while(model.getRowCount() > 0) {
                userName = (String) model.getValueAt(0, 0);
                StaticData.userConMap.get(userName).serverDisconnect();
                StaticData.userConMap.remove(userName);
                model.removeRow(0);
            }
            conNum.setText("0");
        } catch(RemoteException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_discAllButMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableMem;
    private javax.swing.JLabel conNum;
    private javax.swing.JTable conTable;
    private javax.swing.JPanel cpuPanel;
    private javax.swing.JButton discAllBut;
    private javax.swing.JButton disconnectBut;
    private javax.swing.JTextField ipField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField portField;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel usedMem;
    // End of variables declaration//GEN-END:variables
}
