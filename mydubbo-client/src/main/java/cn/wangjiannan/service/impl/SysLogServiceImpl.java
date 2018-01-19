package cn.wangjiannan.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.SysLogMapper;
import cn.wangjiannan.model.SysLog;
import cn.wangjiannan.service.SysLogService;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
