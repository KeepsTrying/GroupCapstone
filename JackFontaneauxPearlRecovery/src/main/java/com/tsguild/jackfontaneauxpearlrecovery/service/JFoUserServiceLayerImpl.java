/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxUserDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author scooke11
 */
public class JFoUserServiceLayerImpl implements JFoUserServiceLayer  {
    
    private JackFontaneauxUserDao userDao;

    public void setUserDao(JackFontaneauxUserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    public JFoUserServiceLayerImpl(JackFontaneauxUserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    public void addUser(User newUser) {
        userDao.addUser(newUser);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void editUser(User editedUser) {
        userDao.editUser(editedUser);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public String getUserRole(int userId) {
        return userDao.getUserRole(userId);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
}
