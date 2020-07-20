package me.hibiki.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.hibiki.crm.domain.Role;
import me.hibiki.crm.domain.User;
import me.hibiki.crm.domain.UserRole;
import me.hibiki.crm.service.RoleService;
import me.hibiki.crm.service.UserService;
import me.hibiki.crm.util.ResultEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/7/16 15:50
 */
@Controller
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @PostMapping(path = "/login")
    @ResponseBody
    public ResultEntity<UserRole> login(@Validated User user, boolean rememberMe, HttpSession httpSession) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getUserPassword(),rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            return new ResultEntity<UserRole>().code(-1).message("用户名或密码错误");
        }
        User loginUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, user.getUserName()));
        httpSession.setAttribute("loginUser", loginUser);
        Role role = roleService.listByUserId(loginUser.getUserId());
        UserRole userRole = new UserRole();
        userRole.setUser(loginUser);
        userRole.setRole(role);
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        return new ResultEntity<UserRole>().code(1).message("登录成功").rows(userRoles);
    }

    @GetMapping(path = "/admin/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.html";
    }

}
