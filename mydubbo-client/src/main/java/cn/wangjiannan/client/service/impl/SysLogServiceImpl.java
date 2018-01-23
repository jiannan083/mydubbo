package cn.wangjiannan.client.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.api.model.SysLog;
import cn.wangjiannan.client.mapper.SysLogMapper;
import cn.wangjiannan.client.service.SysLogService;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
