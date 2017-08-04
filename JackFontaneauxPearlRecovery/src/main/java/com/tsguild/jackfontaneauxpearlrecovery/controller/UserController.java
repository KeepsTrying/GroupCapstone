/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoImageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoTagServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoUserServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class UserController {
    private JFoCategoryServiceLayer categoryServiceLayer;
    private JFoImageServiceLayer imageServiceLayer;
    private JFoTagServiceLayer tagServiceLayer;
    private JFoUserServiceLayer userServiceLayer;
    private JFoPostServiceLayer postServiceLayer;
    private JFoStaticPageServiceLayer staticPageServiceLayer;
    private PasswordEncoder encoder;

    @Inject
    public UserController(JFoCategoryServiceLayer categoryServiceLayer, JFoImageServiceLayer imageServiceLayer,
            JFoTagServiceLayer tagServiceLayer, JFoUserServiceLayer userServiceLayer,
            JFoPostServiceLayer postServiceLayer, JFoStaticPageServiceLayer staticPageServiceLayer,
            PasswordEncoder encoder) {
        this.categoryServiceLayer = categoryServiceLayer;
        this.imageServiceLayer = imageServiceLayer;
        this.tagServiceLayer = tagServiceLayer;
        this.userServiceLayer = userServiceLayer;
        this.postServiceLayer = postServiceLayer;
        this.staticPageServiceLayer = staticPageServiceLayer;
        this.encoder = encoder;
    }
    
    // This endpoint retrieves all users from the database and puts the
    // List of users on the model
    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = userServiceLayer.getAllUsers();
        model.put("users", users);
        return "adminUserFxn";
    }
    // This endpoint just displays the Add User form

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUserForm";
    }
    // This endpoint processes the form data and creates a new User

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();
        // This example uses a plain HTML form so we must get values 
        // from the request
        newUser.setUserName(req.getParameter("username"));
        String unhashedPW = req.getParameter("password");
        String hashedPW = encoder.encode(unhashedPW);
        newUser.setPassword(hashedPW);
        newUser.setEmail(req.getParameter("email"));
        newUser.setEnabled(1);
        newUser.setFirstName(req.getParameter("first-name"));
        newUser.setLastName(req.getParameter("last-name"));
        newUser.setLatitude(new BigDecimal(req.getParameter("latitude")));
        newUser.setLongitude(new BigDecimal(req.getParameter("longitude")));
        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin 
        // box is checked
        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
            newUser.setRole("Admin");
        }
        if (null == req.getParameter("isAdmin")) {
            newUser.setRole("User");
        }

        userServiceLayer.addUser(newUser);

        return "redirect:displayUserList";
    }
    // This endpoint deletes the specified User

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deletUser(@RequestParam("userId") int userId,
            Map<String, Object> model) {
        User user = userServiceLayer.getUserById(userId);
        userServiceLayer.deleteUser(user);
        return "redirect:displayUserList";
    }
    
}
