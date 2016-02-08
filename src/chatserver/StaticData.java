/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver;

import chat.remote.interfaces.ClientRemoteItfz;
import chat.remote.interfaces.ServerRemoteItfz;
import chatserver.GUI.MainScreen;
import java.util.HashMap;


/**
 *
 * @author ESa10969
 */
public class StaticData {
  public static final String tableName = "CHAT_USERS";
  public static MainScreen mainScr;
  public static ServerRemoteItfz srvInt;
  public static HashMap<String, ClientRemoteItfz> userConMap;
}
