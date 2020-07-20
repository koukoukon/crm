package me.hibiki.crm.configuration;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.hibiki.crm.domain.User;
import me.hibiki.crm.service.MenuService;
import me.hibiki.crm.service.RoleService;
import me.hibiki.crm.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 高弘昆
 * @date 2020/7/17 22:31
 */

public class PermissionRealm extends AuthorizingRealm {
    {
        super.setName("permissionRealm");
    }

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();

        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByRoleName(roles);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从主体传过来的用户信息中获得用户名
        String userName = (String) token.getPrincipal();
        //通过用户名到数据库中获取凭证
        String userPassword = getUserPasswordByUserName(userName);
        if (userPassword == null) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, userPassword, getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
        return simpleAuthenticationInfo;
    }

    private String getUserPasswordByUserName(String userName) {
        List<Object> list = userService.listObjs(new LambdaQueryWrapper<User>().select(User::getUserPassword).eq(User::getUserName, userName));
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                return (String) list.get(0);
            }
        }
        return null;
    }

    private Set<String> getPermissionsByRoleName(Set<String> roles) {
        List<String> permissionList = menuService.listPermissionByRoleName(roles);
        return new HashSet<>(permissionList);
    }

    private Set<String> getRolesByUserName(String userName) {
        List<String> roleNameList = roleService.listNameByUserName(userName);
        return new HashSet<>(roleNameList);
    }
}
