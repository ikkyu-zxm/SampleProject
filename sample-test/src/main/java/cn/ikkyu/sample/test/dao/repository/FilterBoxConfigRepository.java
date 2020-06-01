package cn.ikkyu.sample.test.dao.repository;

import cn.ikkyu.sample.test.dao.po.FilterBoxConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xinming
 * @Date 2019/12/23 22:30
 */
public interface FilterBoxConfigRepository extends JpaRepository<FilterBoxConfigPo,Long> {

    /**
     * 通过code查询下拉框
     * @param codeList
     * @return
     */
    List<FilterBoxConfigPo> findByCodeIn(List<String> codeList);

}
