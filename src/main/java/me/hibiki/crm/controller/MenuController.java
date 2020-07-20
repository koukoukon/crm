package me.hibiki.crm.controller;

import me.hibiki.crm.domain.Menu;
import me.hibiki.crm.domain.User;
import me.hibiki.crm.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/7/15 16:58
 */
@RestController
@RequestMapping(path = "/admin/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping
    public List<Menu> getMenusByRoleId(@SessionAttribute(name = "loginUser") User loginUser) {
        return menuService.listByRoleId(loginUser.getRoleId());
    }

}
