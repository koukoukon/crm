package me.hibiki.crm.configuration;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 高弘昆
 * @date 2020/7/17 22:31
 */
@Configuration
public class ShiroConfig {


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }
    @Bean
    public PermissionRealm permissionRealm(@Autowired HashedCredentialsMatcher hashedCredentialsMatcher) {
        PermissionRealm permissionRealm = new PermissionRealm();
        permissionRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return permissionRealm;
    }


    @Bean
    public SecurityManager securityManager(@Autowired PermissionRealm permissionRealm,@Autowired CookieRememberMeManager cookieRememberMeManager,@Autowired DefaultWebSessionManager defaultWebSessionManager,@Autowired CacheManager cacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(permissionRealm);
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        defaultWebSecurityManager.setCacheManager(cacheManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        map.put("/", "anon");
        map.put("/html/login.html", "anon");
        map.put("/admin/logout", "logout");
//        //对所有用户认证
        map.put("/admin/**", "user");
        map.put("/html/admin/**", "user");

        //登录
        shiroFilterFactoryBean.setLoginUrl("/login.html");
//        //首页
//        shiroFilterFactoryBean.setSuccessUrl("/html/admin/main.html");
//        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/html/error.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return  authorizationAttributeSourceAdvisor;
    }
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(@Autowired SimpleCookie simpleCookie){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(Base64.decode("Hibiki"));
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return  defaultWebSessionManager;
    }
    @Bean
    public CacheManager cacheManager(){
        return new MemoryConstrainedCacheManager();
    }
}
