/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.mapper.TagMapper;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
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
public class JackFontaneauxTagDaoDbImpl implements JackFontaneauxTagDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_TAG = "insert into Tags "
            + "(Tag) values (?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag addTag(Tag newTag) {
        jdbcTemplate.update(SQL_ADD_TAG,
                newTag.getTagName());
        int tagId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newTag.setTagId(tagId);
        return newTag;
    }

    private static final String SQL_REMOVE_TAG_POST_BRIDGE = "delete from PostsTags "
            + "where TagId = ?";
    private static final String SQL_REMOVE_TAG = "delete from Tags "
            + "where TagId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteTag(int tagId) {
        jdbcTemplate.update(SQL_REMOVE_TAG_POST_BRIDGE, tagId);
        jdbcTemplate.update(SQL_REMOVE_TAG, tagId);
    }

    private static final String SQL_UPDATE_TAG = "update Tags "
            + "set Tag = ? where TagId = ?";

    @Override
    public void editTag(Tag editedTag) {
        jdbcTemplate.update(SQL_UPDATE_TAG,
                editedTag.getTagName(),
                editedTag.getTagId());

    }

    private static final String SQL_GET_TAG_BY_ID = "select * from Tags "
            + "where TagId = ?";

    @Override
    public Tag getTagById(int tagId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_TAG_BY_ID,
                    new TagMapper(),
                    tagId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_TAGS = "select * from Tags "
            + "order by TagId ASC";

    @Override
    public List<Tag> getAllTags() {

        try {
            return jdbcTemplate.query(SQL_GET_ALL_TAGS, new TagMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_TAGS_BY_NAME
            = "SELECT * FROM Tags WHERE Tag LIKE ?;";

    @Override
    public List<Tag> getTagsByName(String tagName) {
        String insert = "%" + tagName + "%";
        List<Tag> tagList = new ArrayList<>();
        try {
            return tagList = jdbcTemplate.query(SQL_GET_TAGS_BY_NAME, new TagMapper(), insert);
        } catch (EmptyResultDataAccessException e) {
            return tagList;
        }
    }

}
