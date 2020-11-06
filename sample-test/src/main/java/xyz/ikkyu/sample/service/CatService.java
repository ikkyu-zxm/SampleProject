package xyz.ikkyu.sample.service;

import xyz.ikkyu.sample.domain.req.CatReqVO;
import xyz.ikkyu.sample.domain.resp.CatRespVO;

import java.util.List;

/**
 * @author xinming
 * @Date 2019/12/28 9:52
 */
public interface CatService {
    /**
     * 测试explain 查询
     * @return
     */
    Long getCatValuation();

    /**
     * 保存示例
     * @param reqVO
     * @return
     */
    Boolean saveCat(CatReqVO reqVO);

    /**
     * 通过名称查询示例
     * @param name
     * @return
     */
    List<CatRespVO> searchByName(String name);
}
