package com.jushu.video.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.entity.GmOperation;
import com.jushu.video.service.IGmOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/video/gm-operation")
public class GmOperationController {

    private Logger logger = LoggerFactory.getLogger(GmOperationController.class);


    @Autowired
    private IGmOperationService iGmOperationService;


    @RequestMapping("/list")
    public ModelAndView list(Page page, GmOperation gmOperation, ModelAndView model) {
        if(page.getCurrent() == 0) {
            page.setCurrent(1);
        }
        page.setSize(10);
        Page<GmOperation> pages = iGmOperationService.operationPageList(page, gmOperation);
        List<GmOperation> operationList = pages.getRecords();
        model.addObject("operationList", operationList);
        model.addObject("pages", pages);
        model.setViewName("webLogList");
        return model;
    }

}
