package com.lt.controller;

import cn.hutool.core.io.FileUtil;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.constant.FileConstant;
import com.lt.exception.BusinessException;
import com.lt.mananger.CosManager;
import com.lt.service.UserService;
import com.lt.utils.UserThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 22:23
 */
@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件上传控制器")
public class FileController {
    @Resource
    private CosManager cosManager;

    @PostMapping("/upload/image")
    @ApiOperation("图片上传")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<String> uploadImageFile(@RequestPart("file") MultipartFile multipartFile) {
        // 校验图片文件
        validImageFile(multipartFile);
        // 文件目录：根据业务、用户来划分
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        Integer userId = UserThreadLocalUtil.getUserId();
        String filepath = String.format("/%s/%s", userId, filename);
        File file = null;
        try {
            // 上传文件
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            File finalFile = file;
            cosManager.putObject(filepath, finalFile);
            String videoThumbnailUrl = FileConstant.COS_HOST + filepath;
            // 返回可访问地址
            return ResultUtils.success(videoThumbnailUrl);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
    }

    /**
     * 校验图片文件
     */
    private void validImageFile(MultipartFile multipartFile) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 2 * 1024 * 1024L;
        if (fileSize > ONE_M) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片文件大小不能超过 2M");
        }
        if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片文件类型错误");
        }
    }
}
