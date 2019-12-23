package cn.ikkyu.ftp.service;

import java.util.List;

public interface FileService {
    void uploadFile(List<String> paths);

    void batchUploadFile(String directory);
}
