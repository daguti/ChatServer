/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver.remote;

import chat.remote.interfaces.ClientRemoteItfz;
import chat.remote.interfaces.ServerRemoteItfz;
import chatserver.persistance.DAO.PersistanceDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ESa10969
 */
public class ServerRemote extends UnicastRemoteObject implements ServerRemoteItfz {
    
    HashMap<String, ClientRemoteItfz> userConMap = new HashMap<>();
    HashMap<String, String> usrStates            = new HashMap<>();
    PersistanceDAO dao                           = new PersistanceDAO();

    public ServerRemote() throws RemoteException {}
    
    @Override
    public boolean loginUser(String user, String pass, ClientRemoteItfz cli) throws RemoteException {
        //Variable definition
        
        if(dao.loginUser(user, pass)) {
            userConMap.put(user,cli);
            for(ClientRemoteItfz client : userConMap.values()) client.updateConnectedUsers(userConMap);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void logoutUser(String userName) throws RemoteException {
        userConMap.remove(userName);
        for(ClientRemoteItfz client : userConMap.values()) client.updateConnectedUsers(userConMap);
    }

    @Override
    public String send(String msg, StyledDocument msgDocument,ClientRemoteItfz cli, String userFrom) throws RemoteException {
        cli.write(msg, msgDocument,userFrom);
        return "OK";
    }

    @Override
    public HashMap<String, ClientRemoteItfz> getConnectedUsers() throws RemoteException {
        return userConMap;
    } 

    @Override
    public boolean signIn(String user, String pass) throws RemoteException {
        return dao.SingnIn(user, pass);
    }

    @Override
    public void sendFile(String fileName, byte[] bytes, ClientRemoteItfz cli, String userFrom) throws RemoteException {
        cli.receiveFile(fileName, bytes, userFrom);
    }

    @Override
    public void setProfileImage(String user, byte[] bytes) throws RemoteException {
        dao.storeProfileImage(user, bytes);
    }

    @Override
    public void getProfileImage(String user, ClientRemoteItfz cli) throws RemoteException {
        dao.getProfileImage(user, cli);
    }

    @Override
    public void sendAudioClip(String user, ClientRemoteItfz cli, byte[] bytes) {
        cli.getAudioClip(user, bytes);
    }
}
