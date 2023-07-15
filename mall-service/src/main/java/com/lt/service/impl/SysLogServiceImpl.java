package com.lt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.SysLog;
import com.lt.mapper.SysLogMapper;
import com.lt.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【sys_log(系统日志表)】的数据库操作Service实现
 * @createDate 2023-06-10 10:57:55
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
        implements SysLogService {
}




