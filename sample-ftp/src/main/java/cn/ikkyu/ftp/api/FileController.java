package cn.ikkyu.ftp.api;

import cn.ikkyu.ftp.domain.PictureType;
import cn.ikkyu.ftp.domain.ProductQualificationReqVo;
import cn.ikkyu.ftp.domain.PushArchiveVo;
import cn.ikkyu.ftp.feign.TestFeignClient;
import cn.ikkyu.ftp.service.FileService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xinming
 * @Date 2019/11/23 10:52
 */
@Slf4j
@Api("经营模式管理")
@RestController()
@RequestMapping("sample/ftp/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    public TestFeignClient testFeignClient;

    @PostMapping("/uploadFile")
    @ApiOperation("文件上传")
    public void uploadFile(@RequestBody List<String> paths) {

//        log.info("str {}",pathsStr);
//
//        List<String> strings = JSON.parseArray(pathsStr, String.class);

        log.info("文件开始上传");
        fileService.uploadFile(paths);
    }


    @PostMapping("/uploadFileDirectory")
    @ApiOperation("文件上传")
    public Boolean batchUploadFile(String directory) {
        fileService.batchUploadFile(directory);
        return Boolean.TRUE;
    }



    @GetMapping("/test")
    @ApiOperation("文件上传")
    public void upload() {

        log.info("文件开始上传");
    }


    @GetMapping("transfer/test")
    public void testTransfer() {
        PushArchiveVo pushArchiveVo = new PushArchiveVo();
        pushArchiveVo.setAlcohol("324");
        pushArchiveVo.setBarCode("89877777");
        pushArchiveVo.setBatchNumber("43535");

        ProductQualificationReqVo productPicture= new ProductQualificationReqVo();
        productPicture.setGoodsQualificationTypeCode(PictureType.GOODS_PICTURE);
        productPicture.setQualificationPicture("商品图片");

        ProductQualificationReqVo productQualificationReqVo = new ProductQualificationReqVo();
        productQualificationReqVo.setGoodsQualificationTypeCode(PictureType.CUSTOMS_DECLARATION);
        productQualificationReqVo.setQualificationPicture(PictureType.CUSTOMS_DECLARATION.getDesc());
        ArrayList<ProductQualificationReqVo> pictureList = Lists.newArrayList(productQualificationReqVo,productPicture);
        pushArchiveVo.setQualificationList(pictureList);

        testFeignClient.testTransfer(pushArchiveVo);
    }
}
