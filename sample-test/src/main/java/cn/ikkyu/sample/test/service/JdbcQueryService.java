package cn.ikkyu.sample.test.service;

import cn.ikkyu.sample.test.domain.resp.StoreRespVo;

import java.util.List;
import java.util.Map;

/**
 * @author xinming
 * @Date 2020/5/30 11:07
 */
public interface JdbcQueryService {

    /**
     * 查询
     * @param param
     * @return
     */
    List<StoreRespVo> query(Map<String, Object> param);
}
