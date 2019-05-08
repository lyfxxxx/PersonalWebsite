package com.hfut.pw.controller;

import com.hfut.pw.domain.project;
import com.hfut.pw.service.projectsService;
import com.hfut.pw.util.resultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="/api")
public class projectsController {
    @Resource
    private projectsService ps;

    @CrossOrigin
    @RequestMapping(value="/projects",  method= RequestMethod.GET)
    @ResponseBody
    public Object getProjects() {
        List<project> list = ps.getProjects();
        return resultFactory.buildSuccessRes(list);
    }
}
