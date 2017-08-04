/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class JFoTagServiceLayerImplTest extends ServiceTestingBase {

    /**
     * Test of getVerifiedAssociatedTags method, of class JFoTagServiceLayerImpl.
     */
    @Test
    public void testGetVerifiedAssociatedTags() {
        String insertedTagList = " #awesome, #dry, #newTag  ";
        List<Tag> verifiedTags = tagServ.getVerifiedAssociatedTags(insertedTagList);
        List<Tag> allTags = tagServ.getAllTags();
        Assert.assertEquals(5, allTags.size());
        Assert.assertEquals(3, verifiedTags.size());
    }
    
}
