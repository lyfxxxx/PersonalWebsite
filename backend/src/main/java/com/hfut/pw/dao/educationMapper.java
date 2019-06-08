package com.hfut.pw.dao;

import com.hfut.pw.domain.education;

import java.util.List;
import java.util.Map;

public interface educationMapper {
    public List<education> getEducations();
    public education getEducationById(Integer id);
    public void updateEduById(Map<String, Object> map);
    public void deleteEduById(Integer id);
    public void insertEdu(Map<String, Object> map);
}
