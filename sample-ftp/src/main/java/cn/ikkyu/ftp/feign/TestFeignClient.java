package cn.ikkyu.ftp.feign;

import cn.ikkyu.ftp.domain.PushArchiveVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xinming
 */
@FeignClient(qualifier = "testFeignClient", name = "local-test")
public interface TestFeignClient {



    @PostMapping("sample/test/transfer")
    void testTransfer(@RequestBody PushArchiveVo sapOrders);
}
