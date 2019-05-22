package io.fqzero.shiroauth.shiro;

import io.fqzero.shiroauth.entity.Permission;
import io.fqzero.shiroauth.entity.Role;
import io.fqzero.shiroauth.entity.User;
import io.fqzero.shiroauth.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;

@Component
public class CustomRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        logger.info("Authorization: "+"授权");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principal.getPrimaryPrincipal();

        //设置角色的权限信息
        for (Role role : user.getRoleList()) {
            //设置角色
            authorizationInfo.addRole(role.getRole());
            logger.info("Role:"+role.getRole());
            for (Permission p : role.getPermissions()) {
                //设置权限
                authorizationInfo.addStringPermission(p.getPermission());
                logger.info("Permission:"+p.getPermission());
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        logger.info("AuthenticationInfo Token: "+"认证");
        //获取输入的账号
        String username =  auth.getPrincipal().toString();
        logger.info("username: " + username);
        if (username == null) {
            throw new AccountException("The User not Allowed here");
        }

        User userInfo = userService.findByUsername(username);

        if (userInfo == null) {
            throw new UnknownAccountException("No User Found by " + username);
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()), //username+salt
                getName());

        return info;
    }

}
