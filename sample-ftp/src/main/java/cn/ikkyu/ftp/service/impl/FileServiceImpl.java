package cn.ikkyu.ftp.service.impl;

import cn.ikkyu.ftp.config.FtpConfig;
import cn.ikkyu.ftp.service.FileService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author xinming
 * @Date 2019/11/23 10:54
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    private FtpConfig ftpConfig;


    @Override
    public void uploadFile(List<String> paths) {
        FTPClient ftpClient = null;
        try {
            FTPClient client = ftpConfig.getFtpClient();
            ftpClient = client;

            int reply;
            reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                return ;
            }
            String supplierNameDir = "/1223112";

            if (!client.changeWorkingDirectory(supplierNameDir)) {
                client.makeDirectory(supplierNameDir);
                client.changeWorkingDirectory(supplierNameDir);
            }

            Optional.of(paths).orElse(Lists.newArrayList())
                    .stream()
                    .filter(StringUtils::isNotEmpty)
                    .flatMap(path -> Arrays.stream(StringUtils.split(path, ",")))
                    .forEach(path -> {
                        File file = new File(path);
                        try  (InputStream fileInputStream = new FileInputStream(file)){

                            String fileExtension = path.substring(path.lastIndexOf("."));
                            String newFileName = df1.format(new Date()) + "_" + new SecureRandom().nextInt(1000) + fileExtension;

//                            String supplierNameDir = "/" + path;


                            boolean b = uploadFile("1223112", newFileName, fileInputStream, client);
                            log.info("上传结果 ：{}  path {}",b,path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
            ftpClient.logout();
        } catch (Exception e) {
            log.error("图片上传异常");
            e.printStackTrace();
        } finally {
            if (ftpClient != null && ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }

    @Override
    public void batchUploadFile(String directory) {
        File fi = new File(directory);
        List<File> files = new ArrayList<>();
        if (fi.isDirectory()) {
            files = batchUploadFile(fi);
        } else {

        }


        FTPClient ftpClient = null;

        try {
            String  supplierNameDir = "1223112";
            FTPClient client = ftpConfig.getFtpClient();
            ftpClient = client;

            int reply;
            reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                return ;
            }

            if (!client.changeWorkingDirectory(supplierNameDir)) {
                client.makeDirectory(supplierNameDir);
                client.changeWorkingDirectory(supplierNameDir);
            }

            files.forEach(file -> {

                try  (InputStream fileInputStream = new FileInputStream(file)){
                    String name = file.getName();

                    String fileExtension = name.substring(name.lastIndexOf("."));
                    String newFileName = df1.format(new Date()) + "_" + name;

    //                            String supplierNameDir = "/" + path;


                    boolean b = uploadFile(supplierNameDir, newFileName, fileInputStream, client);
                    log.info("上传结果 ：{}  path {}",b,name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {


        } finally {
            if (ftpClient != null && ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }


    }


    private List<File> batchUploadFile(File file) {
        ArrayList<File> files = Lists.newArrayList();
        if (file.isDirectory()) {
            //如果是文件夹
            File[] tempList = file.listFiles();
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isDirectory()) {
                    List<File> batchUploadFile = batchUploadFile(tempList[i]);
                    files.addAll(batchUploadFile);
                } else {
                    files.add(tempList[i]);
                }
            }
            return files;
        } else {
            //如果是文件
            files.add(file);
            return files;
        }
    }




    public boolean uploadFile(String supplierNameDir,String filename, InputStream input,FTPClient ftpClient)  {

        boolean success = false;
//        String path = "/" + supplierNameDir;
        try {

//            if (!ftpClient.changeWorkingDirectory(path)) {
//                ftpClient.makeDirectory(path);
//                ftpClient.changeWorkingDirectory(path);
//            }
            ftpClient.storeFile(filename, input);

            success = true;
        } catch (IOException e) {
            log.error("NameDir {} , fileName{} , input{} ",supplierNameDir,filename,input );
            log.error("exception {}",e);
        }
        if (log.isDebugEnabled()) {
            log.debug("图片上传状态{}",success);
        }
        return success;
    }

}
