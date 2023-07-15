package com.lt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.SysLog;
import org.springframework.stereotype.Repository;

/**
 * @author teng
 * @description 针对表【sys_log(系统日志表)】的数据库操作Mapper
 * @createDate 2023-06-10 10:57:55
 * @Entity com.lt.entity.SysLog
 */
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {
}
