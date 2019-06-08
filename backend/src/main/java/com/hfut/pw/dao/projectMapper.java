package com.hfut.pw.dao;

import com.hfut.pw.domain.project;

import java.util.List;
import java.util.Map;

public interface projectMapper {
    public List<project> getProjects();
    public project getProjById(Integer id);
    public void updateProjById(Map<String, Object> map);
    public void deleteProjById(Integer id);
    public void insertProj(Map<String, Object> map);
}
