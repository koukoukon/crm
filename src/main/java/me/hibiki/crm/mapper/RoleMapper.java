package me.hibiki.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.hibiki.crm.domain.Role;

import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/7/16 17:22
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectNameByUserName(String userName);
    Role selectByUserId(Integer userId);

}