package com.hfut.pw.service;

import com.hfut.pw.dao.imageMapper;
import com.hfut.pw.dao.personalInfoMapper;
import com.hfut.pw.domain.personalInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("indexService")
public class indexService {
    @Resource
    private personalInfoMapper pim;
    @Resource
    private imageMapper im;

    public List<personalInfo> getPersonalInfo() {
        List<personalInfo> list = null;
        list = pim.getPersonInfo();
        return list;
    }

    public String getPersonalPhoto() {
        String path = im.selectPersonalPhoto();
        if(path == null) {
            return "No photo";
        } else {
            return path;
        }
    }

    public void changePersonalPhoto(String path) {
        String oldPath = im.selectPersonalPhoto();
        if(oldPath != null) {
            im.deletePersonalPhoto();
        }
        im.insertPhoto(path, "personal");
    }
}
