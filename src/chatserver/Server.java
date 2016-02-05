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
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ESa10969
 */
public class Server {

  public static void StartServer() {    
    try {
      VerifyTable.verifyTable(PersistanceDAO.getConnection());
      ServerRemoteItfz svrInt = new ServerRemote();
      //ServerRemote svr = new ServerRemote();
      //ServerRemoteItfz stub = (ServerRemoteItfz) UnicastRemoteObject.exportObject(svr, 0);
      
      //Registry registry = LocateRegistry.getRegistry();
      //registry.bind("ChatServer", stub);
      LocateRegistry.createRegistry(1099);
      Naming.rebind("rmi://localhost:1099/ChatServer", svrInt);
      System.out.println("[System] Chat Server is ready.");
    } catch (AccessException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (RemoteException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      } catch (MalformedURLException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
}
  
