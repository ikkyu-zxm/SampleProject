package cn.ikkyu.sample.test.dao.repository;

import cn.ikkyu.sample.test.dao.po.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author xinming
 * @Date 2019/12/23 22:30
 */
public interface CatRepository extends JpaRepository<Cat,Long> {

    /**
     * 测试explain查询总数，数值不精确但查询速度快
     * @return
     */
    @Query(value = "explain select count(*) from Cat",nativeQuery = true)
    Map<String,Object> getCount();

    /**
     * 通过名称查询
     * @param username
     * @return
     */
    List<Cat> findByUserNameLike(String username);
}
