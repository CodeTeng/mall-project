package com.lt.controller;

import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.exception.BusinessException;
import com.lt.service.AddressService;
import com.lt.vo.AddressVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 17:57
 */
@RestController
@RequestMapping("/address")
@Api(tags = "地址控制器")
public class AddressController {
    @Resource
    private AddressService addressService;

    @GetMapping("/getAddress")
    @ApiOperation("获取对应地址名称")
    public BaseResponse<List<AddressVO>> getAddressRegion(String addressAreaId) {
        if (StringUtils.isBlank(addressAreaId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<AddressVO> addressList = addressService.getAddressRegion(addressAreaId);
        return ResultUtils.success(addressList);
    }
}
