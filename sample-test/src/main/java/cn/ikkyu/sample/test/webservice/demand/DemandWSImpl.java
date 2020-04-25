package cn.ikkyu.sample.test.webservice.demand;

import cn.ikkyu.sample.test.domain.RpcRst;
import cn.ikkyu.sample.test.domain.SapDemandWsVo;
import lombok.extern.slf4j.Slf4j;

import javax.jws.WebService;


/**
 * @author xinming
 */
@Slf4j
@WebService(serviceName = "demandWS",
        targetNamespace = "http://demand.webservice.test.sample.ikkyu.cn/",
        endpointInterface = "cn.ikkyu.sample.test.webservice.demand.DemandWS")
public class DemandWSImpl implements DemandWS {


    @Override
    public RpcRst createDemand(SapDemandWsVo[] items) {
        RpcRst rst = new RpcRst();
        log.info("接收到sap采购需求单 {}",items);
        if (items == null || items.length == 0) {
            log.error("下发数据为空");
            rst.setCode(-1);
            rst.setMessage("下发数据为空");
            return rst;
        }
        try {
            Boolean receiveStatus = Boolean.TRUE;
            if (receiveStatus == null || !receiveStatus) {
                rst.setCode(-1);
                rst.setMessage("接收数据失败");
            } else {
                rst.setCode(0);
                rst.setMessage("操作成功");
            }
            return rst;
        } catch (Exception e) {
            e.printStackTrace();
            rst.setCode(-1);
            rst.setMessage("错误:" + e.getMessage());
            return rst;
        }
    }

}
