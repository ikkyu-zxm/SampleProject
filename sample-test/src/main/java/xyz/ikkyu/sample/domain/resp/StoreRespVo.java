package xyz.ikkyu.sample.domain.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xinming
 * @Date 2020/5/31 14:15
 */
@Data
@ApiModel("门店列表")
public class StoreRespVo implements Serializable {
    private static final long serialVersionUID = -7646441008828099117L;
}
