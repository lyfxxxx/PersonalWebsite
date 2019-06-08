package com.hfut.pw.controller;

import com.hfut.pw.domain.education;
import com.hfut.pw.domain.project;
import com.hfut.pw.service.projectsService;
import com.hfut.pw.util.resultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseBody
    public Object getProject(@RequestParam(value = "id") Integer id) {
        project proj = ps.getProjById(id);
        if(proj != null) {
            return resultFactory.buildSuccessRes(proj);
        } else {
            return resultFactory.buildFailedRes("查询不到该项数据！");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/updateProj", method = RequestMethod.POST)
    @ResponseBody
    public Object updateProj(@RequestBody project proj) {
        if(ps.updateProj(proj)) {
            return resultFactory.buildSuccessRes(null);
        } else {
            return resultFactory.buildFailedRes("传输数据失败或查询不到该项数据！");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteProj", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteProj(@RequestParam(value = "id") Integer id) {
        List<project> list = ps.deleteProj(id);
        if(list != null) {
            return resultFactory.buildSuccessRes(list);
        } else {
            return resultFactory.buildFailedRes("传输数据失败或查询不到该项数据！");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/insertProj", method = RequestMethod.POST)
    @ResponseBody
    public Object insertProj(@RequestBody project proj) {
        if(ps.insertProj(proj)) {
            return resultFactory.buildSuccessRes(null);
        } else {
            return resultFactory.buildFailedRes("传输数据失败");
        }
    }
}
