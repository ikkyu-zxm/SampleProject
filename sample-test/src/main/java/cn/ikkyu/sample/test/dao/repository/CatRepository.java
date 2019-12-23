package cn.ikkyu.sample.test.dao.repository;

import cn.ikkyu.sample.test.dao.po.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xinming
 * @Date 2019/12/23 22:30
 */
public interface CatRepository extends JpaRepository<Cat,Long> {
}
