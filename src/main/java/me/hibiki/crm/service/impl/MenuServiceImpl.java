package me.hibiki.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.hibiki.crm.domain.Menu;
import me.hibiki.crm.mapper.MenuMapper;
import me.hibiki.crm.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author 高弘昆
 * @date 2020/7/15 16:58
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService{
    @Resource
    private MenuMapper menuMapper;
    @Override
    public List<Menu> listByRoleId(Integer roleId) {
        return menuMapper.selectByRoleId(roleId);
    }

    @Override
    public List<String> listPermissionByRoleName(Set<String> roleNames) {
        return menuMapper.selectPermissionByRoleName(roleNames);
    }
}
