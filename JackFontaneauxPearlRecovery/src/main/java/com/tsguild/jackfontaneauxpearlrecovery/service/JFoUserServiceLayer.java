/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.List;

/**
 *
 * @author scooke11
 */
public interface JFoUserServiceLayer {
    
    public void addUser(User newUser);
    
    public void deleteUser(User user);
    
    public void editUser(User editedUser);
    
    public User getUserById(int userId);
    
    public String getUserRole(int userId);
    
    public List<User> getAllUsers();
    
}
