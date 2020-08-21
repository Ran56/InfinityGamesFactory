package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.FileInfo;
import com.infinity.gamesFactory.model.Game;
import com.infinity.gamesFactory.model.User;

import java.util.List;

public interface FileInfoDao {

    FileInfo save(FileInfo fileInfo);
    List<FileInfo> getFileInfos();
    FileInfo getBy(Long id);
    boolean delete(FileInfo fileInfo);

    FileInfo update(FileInfo fileInfo);
    boolean delete(String originalS3Key);
    List<FileInfo> getFileInfosEager();
    FileInfo getFileInfoEagerById(Long id);
    List<FileInfo> getFileInfoByName(String uuidOrOriginalName, User user);



}
