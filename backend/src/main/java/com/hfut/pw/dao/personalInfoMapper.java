package com.hfut.pw.dao;

import com.hfut.pw.domain.personalInfo;

import java.util.List;
import java.util.Map;

public interface personalInfoMapper {
    public List<personalInfo> getPersonInfo();
    public void insertNewInfo(String newInfo);
    public void updateNewInfo(Map<String, Object> map);
}
