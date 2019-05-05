package com.hfut.pw.service;

import com.hfut.pw.dao.personalInfoMapper;
import com.hfut.pw.domain.personalInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("indexService")
public class indexService {
    @Resource
    private personalInfoMapper pim;

    public personalInfo getPersonalInfo() {
        personalInfo pi = pim.getPersonInfo();
        return pi;
    }
}
