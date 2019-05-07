package com.hfut.pw.service;

import com.hfut.pw.dao.personalInfoMapper;
import com.hfut.pw.domain.personalInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("indexService")
public class indexService {
    @Resource
    private personalInfoMapper pim;

    public List<personalInfo> getPersonalInfo() {
        List<personalInfo> list = null;
        list = pim.getPersonInfo();
        return list;
    }
}
