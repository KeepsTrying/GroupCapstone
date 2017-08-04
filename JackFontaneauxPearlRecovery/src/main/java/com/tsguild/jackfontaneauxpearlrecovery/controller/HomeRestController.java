/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author scooke11
 */
@Controller
public class HomeRestController {
    private JFoPostServiceLayer postService;
    
    @Inject
    public HomeRestController(JFoPostServiceLayer postService) {
        this.postService = postService;
    }
   
    @ResponseBody
    @RequestMapping(value="/next6Posts/{lowerBound}", method=RequestMethod.GET)
    public List<Post> getNext6Posts(@PathVariable int lowerBound){
        
        return postService.getPublishedPostsByGroup(lowerBound, 6);
        
    }
    
}
