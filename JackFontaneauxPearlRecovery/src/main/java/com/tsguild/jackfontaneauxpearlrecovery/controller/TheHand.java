/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scooke11
 */
public class TheHand {
   
    private JFoCategoryServiceLayer categoryService;
    private JFoStaticPageServiceLayer pageService;
    
    private List<StaticPage> bioPages = new ArrayList();
    private List<StaticPage> faqPages = new ArrayList();
    private List<StaticPage> contactPages = new ArrayList();
    private List<StaticPage> eventPages = new ArrayList();
    private List<StaticPage> aboutPages = new ArrayList();

    
    public TheHand(JFoCategoryServiceLayer categoryService,
            JFoStaticPageServiceLayer pageService) {
        
        this.categoryService = categoryService;
        this.pageService = pageService;
        
        
        List<StaticPage> allBioPages = pageService.getStaticPagesWithCategory(
                categoryService.getPageCategoryById(1));
        if(allBioPages.isEmpty()){
            this.bioPages = allBioPages;
        } else {
            for(StaticPage bioPage: allBioPages){
                if(bioPage.getIsPublished()){
                    bioPages.add(bioPage);
                }
            }
        }
        
        List<StaticPage> allFaqPages = pageService.getStaticPagesWithCategory(
                categoryService.getPageCategoryById(2));
        if(allFaqPages.isEmpty()){
            this.faqPages = allFaqPages;
        }else{
            for(StaticPage faqPage : allFaqPages){
                if(faqPage.getIsPublished()){
                    faqPages.add(faqPage);
                }
            }
        }
        
        List<StaticPage> allContactPages = pageService.getStaticPagesWithCategory(
                categoryService.getPageCategoryById(3));
        if(allContactPages.isEmpty()){
            this.contactPages = allContactPages;
        }else{
            for(StaticPage contactPage : allContactPages){
                if(contactPage.getIsPublished()){
                    contactPages.add(contactPage);
                }
            }
        }
        
        List<StaticPage> allEventPages = pageService.getStaticPagesWithCategory(
                categoryService.getPageCategoryById(5));
        if(allEventPages.isEmpty()){
            this.eventPages = allEventPages;
        }else{
            for(StaticPage eventPage : allEventPages){
                if(eventPage.getIsPublished()){
                    eventPages.add(eventPage);
                }
            }
        }
        
        List<StaticPage> allAboutPages = pageService.getStaticPagesWithCategory(
                categoryService.getPageCategoryById(6));
        if(allAboutPages.isEmpty()){
            this.aboutPages = allAboutPages;
        }else{
            for(StaticPage aboutPage : allAboutPages){
                if(aboutPage.getIsPublished()){
                    aboutPages.add(aboutPage);
                }
            }
        }
        
    }

    public List<StaticPage> getBioPages() {
        return bioPages;
    }

    public void setBioPages(List<StaticPage> bioPages) {
        this.bioPages = bioPages;
    }

    public List<StaticPage> getFaqPages() {
        return faqPages;
    }

    public void setFaqPages(List<StaticPage> faqPages) {
        this.faqPages = faqPages;
    }

    public List<StaticPage> getContactPages() {
        return contactPages;
    }

    public void setContactPages(List<StaticPage> contactPages) {
        this.contactPages = contactPages;
    }

    public List<StaticPage> getEventPages() {
        return eventPages;
    }

    public void setEventPages(List<StaticPage> eventPages) {
        this.eventPages = eventPages;
    }

    public List<StaticPage> getAboutPages() {
        return aboutPages;
    }

    public void setAboutPages(List<StaticPage> aboutPages) {
        this.aboutPages = aboutPages;
    }
    
    
    
}
