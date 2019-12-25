/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
        
/**
 *
 * @author joona
 */
public class UserStorage {
    private final File users;
    public UserStorage(String location){
        this.users = new File(location);
        

    }
    /**
     * return true, if user added successfully
     * @param user
     * @param password
     * @return 
     */
    public boolean addUser(String user, String password) throws IllegalArgumentException {
        try(FileWriter writer = new FileWriter(users, true);Scanner reader = new Scanner(users);) {
        
        while(reader.hasNext()) {
            String line = reader.nextLine();
        
            String[] userAndPassword=line.split("\t");
            System.out.println(userAndPassword[0]);
            if(userAndPassword[0].equals(user)) {
               throw new IllegalArgumentException("username in use");
            }
        }
        
        
        
        writer.write(user+"\t"+password+"\n");
        
        System.out.println("Added user "+user);
        }catch(IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean checkPassword(String user, String password) {
        try (final Scanner reader = new Scanner(users)) {
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] userAndPassword = line.split("\t");
                if (userAndPassword[0].equals(user) && userAndPassword[1].equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
