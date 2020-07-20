package me.hibiki.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.hibiki.crm.domain.Role;

import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/7/16 17:22
 */
public interface RoleService extends IService<Role>{
        List<String> listNameByUserName(String userName);
        Role listByUserId(Integer userId);
}
