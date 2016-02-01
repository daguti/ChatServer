/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver.persistance;

import java.net.Socket;

/**
 *
 * @author ESa10969
 */
public interface PersistanceItfz {
  public String loginUser(String user, String pass, Socket s);
}
