package com.hfut.pw.dao;

import com.hfut.pw.domain.image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface imageMapper {
    public String selectPersonalPhoto();
    public void insertPhoto(@Param(value = "path") String path, @Param(value = "type") String type);
    public void deletePersonalPhoto();
    public void deletePhoto(@Param(value = "path") String path);
    public List<image> selectFavoritesPhotos();
}
