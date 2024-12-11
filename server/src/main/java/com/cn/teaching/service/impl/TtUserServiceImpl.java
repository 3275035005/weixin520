package com.cn.teaching.service.impl;

import com.cn.teaching.entity.TtUser;
import com.cn.teaching.mapper.TtUserMapper;
import com.cn.teaching.service.TtUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.teaching.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表信息 服务实现类
 * </p>
 */
@Service
public class TtUserServiceImpl extends ServiceImpl<TtUserMapper, TtUser> implements TtUserService {

    @Override
    public PageResult pageQuery(int page, int limit, TtUser data) {
        List<TtUser> queryList = baseMapper.pageQuery(data);
        PageHelper.startPage(page, limit);
        PageInfo<TtUser> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public TtUser getUserInfoById(String id) {
        return baseMapper.getUserInfoById(id);
    }

    @Override
    public void updateUserInfo(TtUser ttUser) {
        baseMapper.updateUserInfo(ttUser);
    }
}
