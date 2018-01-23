package cn.wangjiannan.server.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.api.model.SysLog;
import cn.wangjiannan.api.service.SysLogService;
import cn.wangjiannan.server.mapper.SysLogMapper;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
