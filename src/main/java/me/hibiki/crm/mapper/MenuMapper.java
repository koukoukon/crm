package me.hibiki.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.hibiki.crm.domain.Menu;

import java.util.List;
import java.util.Set;

/**
 * @author 高弘昆
 * @date 2020/7/15 16:58
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectByRoleId(Integer roleId);
    List<String> selectPermissionByRoleName(Set<String> roles);
}