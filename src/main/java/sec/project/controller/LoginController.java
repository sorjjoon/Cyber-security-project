/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import sec.project.data.UserStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Manages login related queries
 */
@Controller
public class LoginController {
    
    /**
     * File which contains all saved user names in plain text cause why not
     */
    private final UserStorage users;
    

    public LoginController() {
        super();
        this.users=new UserStorage("./src/main/resources/data/users.txt");
    }
    
    
    
    @RequestMapping(value="/newuser", method = RequestMethod.GET)
    public String newUserForm() {
        return "newuser";
    }
    
    @RequestMapping(value="/newuser", method = RequestMethod.POST)
    public String createUser(@RequestParam String username, @RequestParam String password) {
        try{
            if(users.addUser(username, password)){
                System.out.println("user: "+username+ " created");
                return "login";
            }
                
        }catch(IllegalArgumentException e) {
            return "usernametaken";
        }
        
        return "error";
    }
    
    
    
    
}
