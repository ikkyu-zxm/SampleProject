package xyz.ikkyu.sample.webservice.demand;


import xyz.ikkyu.sample.domain.RpcRst;
import xyz.ikkyu.sample.domain.SapDemandWsVo;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


/**
 * @author xinming
 */
@WebService
public interface DemandWS {
    /**
     * RpcRst createDemand()
     * 创建采购需求
     *
     * @param items DemandItemWsObj[]   采购需求项数组,每个元素包含以下属性
             String bukrs;	公司代码
             Date badat;		创建日期
             String banfn;		采购申请
             Long bnfpo;	行项目
             String matnr;		商品编码
             Integer menge;	数量
             String eeind;	交货日期
             String werks;	仓库/门店编码
             String zzadrc;	交货地址
             String lgort; 库存地点
             String lgobe; 库存地点描述
     * @return RpcRst 包含:
     * code,message
     */
    @WebMethod(operationName = "createDemand")
    @WebResult(name="rpcRst")
    RpcRst createDemand(SapDemandWsVo[] items);
}
