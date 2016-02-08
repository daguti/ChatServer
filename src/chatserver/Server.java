/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import chat.remote.interfaces.ServerRemoteItfz;
import chatserver.persistance.DAO.PersistanceDAO;
import chatserver.persistance.VerifyTable;
import chatserver.remote.ServerRemote;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ESa10969
 */
public class Server {

    private static String ipO;
    private static int portO;
    
  public static void StartServer(String ip, int port) {    
    try {
      VerifyTable.verifyTable(PersistanceDAO.getConnection());
      ServerRemoteItfz svrInt = new ServerRemote();
      StaticData.srvInt = svrInt;
      //ServerRemote svr = new ServerRemote();
      //ServerRemoteItfz stub = (ServerRemoteItfz) UnicastRemoteObject.exportObject(svr, 0);
      
      //Registry registry = LocateRegistry.getRegistry();
      //registry.bind("ChatServer", stub);
      LocateRegistry.createRegistry(port);
      Naming.rebind("rmi://" + ip + ":" + port + "/ChatServer", svrInt);
      System.out.println("rmi://" + ip + ":" + port + "/ChatServer is ready.");
      ipO   = ip;
      portO = port;
    } catch (AccessException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (RemoteException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (MalformedURLException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public static void StopServer() {    
    try {
      Naming.unbind("rmi://" + ipO + ":" + portO + "/ChatServer");
      System.out.println("rmi://" + ipO + ":" + portO + "/ChatServer is Stopped.");
    } catch (AccessException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (RemoteException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (MalformedURLException | NotBoundException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
}
  
