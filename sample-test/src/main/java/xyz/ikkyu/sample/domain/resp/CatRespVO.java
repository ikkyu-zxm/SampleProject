package xyz.ikkyu.sample.domain.resp;

import lombok.Data;

/**
 * @author xinming
 * @Date 2020/6/27 10:08
 */
@Data
public class CatRespVO {
    private Integer userId;
    private String username;
    private String sex;
    private Integer age;
}