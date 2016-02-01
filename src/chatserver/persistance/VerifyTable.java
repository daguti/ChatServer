/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver.persistance;

import chatserver.StaticData;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ESa10969
 */
public class VerifyTable {
  
  public static void verifyTable(Connection con) {
    //Variable definition
    DatabaseMetaData dbmeta;
    ResultSet rs = null;
    boolean tableExists = false;
    
    try {
      dbmeta = con.getMetaData();
      rs = dbmeta.getTables(null, null, null, new String[]{"TABLE"});
      
      while(rs.next()) {
        if(rs.getString("TABLE_NAME").equals(StaticData.tableName)) {
          tableExists = true;
          break;
        }
      }
      if(!tableExists) {
        createTable(con);
      }
    } catch (SQLException ex) {
      Logger.getLogger(VerifyTable.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if(rs != null) rs.close();
        con.close();
      } catch(SQLException ex) {
        Logger.getLogger(VerifyTable.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  private static void createTable(Connection con) {
    //Variable definition
    Statement st;
    String qry = "CREATE TABLE " + StaticData.tableName + " (USERNAME VARCHAR(20) PRIMARY KEY NOT NULL, "
                                                        + "PASSWORD VARCHAR(20), PROFILE_IMAGE BLOB)";
    System.out.println(qry);
    
    try {
      st = con.createStatement();
      //st.execute("DROP TABLE " + StaticData.tableName);
      st.executeUpdate(qry);
      st.executeUpdate("INSERT INTO " + StaticData.tableName + " (USERNAME, PASSWORD) VALUES ('dagu', '12345678')");
      st.executeUpdate("INSERT INTO " + StaticData.tableName + " (USERNAME, PASSWORD) VALUES ('admin', '12345678')");
    } catch (SQLException ex) {
      Logger.getLogger(VerifyTable.class.getName()).log(Level.SEVERE, null, ex);
    }

    
  }
}
