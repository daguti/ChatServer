/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver.persistance.DAO;

import chat.remote.interfaces.ClientRemoteItfz;
import chatserver.StaticData;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author ESa10969
 */
public class PersistanceDAO {
  public boolean loginUser(String user, String pass) {
    //Variable definition
    Connection con         = null;
    PreparedStatement prSt = null;
    ResultSet rs           = null;
    String qry = "SELECT * FROM " + StaticData.tableName + " WHERE USERNAME = ? AND PASSWORD = ?";
    
    try {
      con = getConnection();
      prSt = con.prepareStatement(qry);
      prSt.setString(1, user);
      prSt.setString(2, pass);
      rs = prSt.executeQuery();
     
      if(rs.next()) {
        return true;
      }
    } catch(SQLException ex) {
      Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
          if(rs != null)  rs.close();
          if(prSt != null)prSt.close();
          if(con != null) con.close();
      } catch (SQLException ex) {
          Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return false;
  }
  
  public boolean SingnIn(String user, String pass) {
    Connection con         = null;
    PreparedStatement prSt = null;
    String qry = "INSERT INTO " + StaticData.tableName + "(USERNAME, PASSWORD) VALUES(?,?)";
    
    try {
      con  = getConnection();
      prSt = con.prepareStatement(qry);
      prSt.setString(1, user);
      prSt.setString(2, pass);
      prSt.execute();
      con.commit();
    } catch(SQLException ex) {
      Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    } finally {
      try {
          if(prSt != null)prSt.close();
          if(con != null) con.close();
      } catch (SQLException ex) {
          Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return true;
  }
  
  public static Connection getConnection() {
    //Variable definition
    String url     = "jdbc:derby:TrcFormatterPersistence;create=true;user=root;password=root";
    Connection con = null;
    
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
      con = DriverManager.getConnection(url);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
      Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return con;
  }
  
  public void storeProfileImage(String userName, byte[] bytes) {
      //Variable definition
      Connection con         = null;
      PreparedStatement prSt = null;
      String qry = "UPDATE " + StaticData.tableName + " SET PROFILE_IMAGE = ? WHERE USERNAME = ?";
      
      try {
          con = getConnection();
          InputStream fis = new ByteArrayInputStream(bytes);
          prSt = con.prepareStatement(qry);
          prSt.setBinaryStream(1, fis);
          prSt.setString(2, userName);
          prSt.execute();
          con.commit();
      } catch (SQLException ex) {
          Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
      try {
          if(prSt != null)prSt.close();
          if(con != null) con.close();
      } catch (SQLException ex) {
          Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  public InputStream getProfileImage(String userName, ClientRemoteItfz cli) {
      //Variable definition
      Connection con         = null;
      PreparedStatement prSt = null;
      ResultSet rs           = null;
      InputStream in         = null;
      String qry = "SELECT PROFILE_IMAGE FROM " + StaticData.tableName + " WHERE USERNAME = ?";

      try {
          con = getConnection();
          prSt = con.prepareStatement(qry);
          prSt.setString(1, userName);
          rs = prSt.executeQuery();
          if(rs.next()) {
            in = rs.getBinaryStream("PROFILE_IMAGE");
            //if(blob == null) return null;  
            //in = blob.getBinaryStream();
            if(in != null) {
                BufferedImage image = ImageIO.read(in);
                if(image != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "png", baos );
                    baos.flush();

                    byte[] bytes = baos.toByteArray();
                    cli.getProfileImage(userName, bytes);
                    baos.close();
                }
                in.close();
            }
          }
      } catch (SQLException ex) {
          Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
      try {
          if(rs != null)  rs.close();
          if(prSt != null)prSt.close();
          if(con != null) con.close();
      } catch (SQLException ex) {
          Logger.getLogger(PersistanceDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
      
      return in;
  }
}
