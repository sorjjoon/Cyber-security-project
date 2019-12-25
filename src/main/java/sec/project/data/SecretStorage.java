/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sec.project.domain.Secret;


public class SecretStorage {
        private final Connection con;

    /**
     * set up a connection to a given location
     *
     * @param location
     * @throws SQLException
     */
    public SecretStorage(String location) throws SQLException {
        try {
            Class.forName("org.h2.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Can't locate h2 driver");
            System.out.println(e);
        }
        //removing .mv.db extension from file name
        if (location.contains(".mv.db")) {
            location = location.replace(".mv.db", "");

        }

        this.con = DriverManager.getConnection("jdbc:h2:" + location, "sa", "");
        this.createTables();

    }
    
    public void createTables() throws SQLException{
        String sql ="CREATE TABLE IF NOT EXISTS Secret (secret TEXT ,user varchar(20) )";
        con.prepareStatement(sql).executeUpdate();
    }
    
    
    public void addSecret(String secret, String user) throws SQLException{
        String sql="INSERT INTO Secret (secret, user) VALUES ('"+secret+"', '"+user+"')";
        con.prepareStatement(sql).executeUpdate();
    }
    
    public List<Secret> getSecrets(String user) throws SQLException {
        String sql="SELECT secret FROM Secret WHERE user like '"+user+"'";
        ResultSet rs=con.prepareStatement(sql).executeQuery();
        List<Secret> results=new ArrayList();
        while(rs.next()) {
            results.add(new Secret(rs.getString(1)));
        }
        return results;
    }
}
