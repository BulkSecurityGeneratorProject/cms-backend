package com.synectiks.cms.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Config;
import com.synectiks.cms.repository.CollegeRepository;

@Component
public class GlobalConfig {

	private final Logger logger = LoggerFactory.getLogger(GlobalConfig.class);
	
	public static Config CONFIG = new Config();
    
	
	@Autowired
    private CollegeRepository collegeRepository;
    
	@Autowired
	private CommonService commonService;
	
    @PostConstruct
    private void init() {
    	logger.info("Initializing cms global settings");
        List<College> collegeList = collegeRepository.findAll();
        if(collegeList != null && collegeList.size() > 0) {
        	CONFIG.setCollege(collegeList.get(0));
        }
        
        CmsConstants.USERS_CACHE.put("admin", commonService.createUserConfigForAdmin("admin"));
        CmsConstants.USERS_CACHE.put("sysadmin", commonService.createUserConfigForAdmin("admin"));
	}
    
}