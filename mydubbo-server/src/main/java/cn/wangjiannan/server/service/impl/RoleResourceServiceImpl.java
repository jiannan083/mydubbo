package cn.wangjiannan.server.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.api.model.RoleResource;
import cn.wangjiannan.api.service.RoleResourceService;
import cn.wangjiannan.server.mapper.RoleResourceMapper;

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

}
