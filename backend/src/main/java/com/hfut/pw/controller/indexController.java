package com.hfut.pw.controller;

import com.hfut.pw.domain.personalInfo;
import com.hfut.pw.service.indexService;
import com.hfut.pw.util.resultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class indexController {
    @Resource
    private indexService is;

    @CrossOrigin
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Object getIndexInfo() {
        List<personalInfo> list = is.getPersonalInfo();
        return resultFactory.buildSuccessRes(list);
    }
}
