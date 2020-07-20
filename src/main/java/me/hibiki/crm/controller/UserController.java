package me.hibiki.crm.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.hibiki.crm.domain.User;
import me.hibiki.crm.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/7/18 15:42
 */
@RestController
@RequestMapping(path = "/admin/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public List<User> list() {
        return userService.list(Wrappers.<User>lambdaQuery().eq(User::getRoleId, 3));
    }
}
