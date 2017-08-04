/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxUserDaoTest extends TestingBase{
    
    
    /**
     * Test of addUser method, of class JackFontaneauxUserDao.
     */
    @Test
    public void testAddGetUser() {
       User newUser = new User();
       newUser.setEmail("pasholar01@gmail.com");
       newUser.setFirstName("Paul");
       newUser.setLastName("Sholar");
       newUser.setPassword("the5password");
       newUser.setEnabled(1);
       newUser.setRole("Admin");
       newUser.setUserName("TheDude");
       newUser.setLatitude(new BigDecimal("55.67"));
       newUser.setLongitude(new BigDecimal("34.34"));
       userDao.addUser(newUser);
       User fromDao = userDao.getUserById(newUser.getUserId());
       assertEquals(fromDao.getEmail(), newUser.getEmail());
    }

    /**
     * Test of deleteUser method, of class JackFontaneauxUserDao.
     */
    @Test
    public void testGetAllDeleteUser() {
       List<User> userList = userDao.getAllUsers();
       assertEquals(2, userList.size());
       assertEquals(true, userList.contains(userDao.getUserById(2)));
    }

    /**
     * Test of editUser method, of class JackFontaneauxUserDao.
     */
    @Test
    public void testEditUser() {
        User toEdit = userDao.getUserById(1);
        assertEquals("Admin", toEdit.getRole());
        toEdit.setRole("Client");
        userDao.editUser(toEdit);
        User fromDao = userDao.getUserById(1);
        assertEquals("Client", fromDao.getRole());
        assertEquals(fromDao, toEdit);
    }

    /**
     * Test of getUserRole method, of class JackFontaneauxUserDao.
     */
    @Test
    public void testGetUserRole() {
       User theOne = userDao.getUserById(1);
       String role = userDao.getUserRole(1);
       assertEquals("Admin", role);
       assertEquals(role, theOne.getRole());
    }

   
    
}
