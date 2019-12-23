package cn.ikkyu.sample.test.api;

import cn.ikkyu.sample.test.domain.BackArchivesGoodsInfoRespVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xinming
 * @Date 2019/11/23 15:51
 */
@Slf4j
@Api("测试controller")
@RestController
@RequestMapping("sample/test")
public class TestController {


    @ApiOperation("测试传输")
    @PostMapping("transfer")
    public void testTransfer(@RequestBody String data) {
        log.info("transfer success. data : {}",data);

        JSONObject jsonObject = JSON.parseObject(data);
        BackArchivesGoodsInfoRespVO backArchivesGoodsInfoRespVO = JSON.parseObject(data, BackArchivesGoodsInfoRespVO.class);

        log.info("JSONObject {}",JSON.toJSONString(backArchivesGoodsInfoRespVO));

    }

    @ApiOperation("测试传输")
    @GetMapping("test")
    public String test() {
        log.info("--------------");
       return "hello";
    }
}
