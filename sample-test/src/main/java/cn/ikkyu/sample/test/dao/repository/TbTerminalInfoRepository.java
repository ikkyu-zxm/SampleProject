package cn.ikkyu.sample.test.dao.repository;

import cn.ikkyu.sample.test.dao.po.TbTerminalInfoPo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xinming
 * @Date 2020/5/31 16:21
 */
public interface TbTerminalInfoRepository extends JpaRepository<TbTerminalInfoPo,Long> {
}
