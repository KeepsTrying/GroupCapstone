/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.mapper.UserMapper;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxUserDaoDbImpl implements JackFontaneauxUserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_USER = "insert into Users "
            + "(UserName, Password, Enabled, Role, FirstName, LastName, Email, Latitude, Longitude) "
            + "values (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_ADD_USER_AUTHORITIES = "insert into Authorities "
            + "(UserName, Authority) values (?,?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User newUser) {
        jdbcTemplate.update(SQL_ADD_USER,
                newUser.getUserName(),
                newUser.getPassword(),
                newUser.getEnabled(),
                newUser.getRole(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(),
                newUser.getLatitude(),
                newUser.getLongitude());
        int userId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newUser.setUserId(userId);

        List<String> authorities = newUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_ADD_USER_AUTHORITIES,
                    newUser.getUserName(),
                    authority);
        }

    }

    private static final String SQL_REMOVE_USER_POST_BRIDGE = "delete from PostsUsers "
            + "where UserId = ?";
    private static final String SQL_REMOVE_USER_PAGE_BRIDGE = "delete from PagesUsers "
            + "where UserId = ?";
    private static final String SQL_REMOVE_AUTHORITIES_USER = "delete from Authorities "
            + "where UserName = ?";
    private static final String SQL_REMOVE_USER = "delete from Users "
            + "where UserId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUser(User user) {
        jdbcTemplate.update(SQL_REMOVE_USER_POST_BRIDGE, user.getUserId());
        jdbcTemplate.update(SQL_REMOVE_USER_PAGE_BRIDGE, user.getUserId());
        jdbcTemplate.update(SQL_REMOVE_AUTHORITIES_USER, user.getUserName());
        jdbcTemplate.update(SQL_REMOVE_USER, user.getUserId());
    }

    private static final String SQL_UPDATE_USER = "update Users set "
            + "UserName = ?, Password = ?, Enabled = ?, Role = ?, FirstName = ?, LastName = ?, Email = ?, "
            + "Latitude = ?, Longitude = ? where UserId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void editUser(User editedUser) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                editedUser.getUserName(),
                editedUser.getPassword(),
                editedUser.getEnabled(),
                editedUser.getRole(),
                editedUser.getFirstName(),
                editedUser.getLastName(),
                editedUser.getEmail(),
                editedUser.getLatitude(),
                editedUser.getLongitude(),
                editedUser.getUserId());
        jdbcTemplate.update(SQL_REMOVE_AUTHORITIES_USER, editedUser.getUserName());
        List<String> authorities = editedUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_ADD_USER_AUTHORITIES,
                    editedUser.getUserName(),
                    authority);
        }
    }

    private static final String SQL_GET_USER_BY_ID = "select * from Users "
            + "where UserId = ?";

    @Override
    public User getUserById(int userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID,
                    new UserMapper(),
                    userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_USER_ROLE = "select Role from Users "
            + "where UserId = ?";

    @Override
    public String getUserRole(int userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_USER_ROLE, String.class, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_USERS = "select * from Users";

    @Override
    public List<User> getAllUsers() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_USERS,
                    new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
