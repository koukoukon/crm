package me.hibiki.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.hibiki.crm.domain.Menu;

import java.util.List;
import java.util.Set;

/**
 * @author 高弘昆
 * @date 2020/7/15 16:58
 */
public interface MenuService extends IService<Menu>{
        List<Menu> listByRoleId(Integer roleId);
        List<String> listPermissionByRoleName(Set<String> roleNames);

}
