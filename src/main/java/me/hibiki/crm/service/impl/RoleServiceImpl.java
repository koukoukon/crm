package me.hibiki.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.hibiki.crm.domain.Role;
import me.hibiki.crm.mapper.RoleMapper;
import me.hibiki.crm.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author 高弘昆
 * @date 2020/7/16 17:22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<String> listNameByUserName(String userName) {
        return roleMapper.selectNameByUserName(userName);
    }

    @Override
    public Role listByUserId(Integer userId) {
        return roleMapper.selectByUserId(userId);
    }
}
