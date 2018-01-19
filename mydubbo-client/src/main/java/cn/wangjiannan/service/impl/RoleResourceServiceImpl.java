package cn.wangjiannan.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.RoleResourceMapper;
import cn.wangjiannan.model.RoleResource;
import cn.wangjiannan.service.RoleResourceService;

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

}
