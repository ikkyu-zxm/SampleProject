package xyz.ikkyu.sample.test.dao.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xinming
 * @Date 2019/12/23 22:33
 */
@Data
@Entity
@Table(name="t_cat")
public class Cat {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String sex;
    private Integer age;


}
