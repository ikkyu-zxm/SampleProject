package cn.ikkyu.ftp.config;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author xinming
 * @Date 2019/11/23 11:14
 */
@Data
@RefreshScope
@ConditionalOnProperty("sample.ftp.config.enabled")
@Configuration
public class FtpConfig {

    @Value("${sample.ftp.config.password}")
    private String password;

    @Value("${sample.ftp.config.username}")
    private String username;

    @Value("${sample.ftp.config.url}")
    private String url;

    @Value("${sample.ftp.config.port}")
    private Integer port;


    public FTPClient getFtpClient() throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(url,port);
        ftp.login(username, password);
        ftp.setControlEncoding("UTF-8");
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
        return ftp;
    }

}
