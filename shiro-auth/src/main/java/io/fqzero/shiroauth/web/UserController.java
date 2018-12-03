package io.fqzero.shiroauth.web;

import io.fqzero.shiroauth.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam String username,@RequestParam String password,
                          Boolean rememberMe) {
        logger.info("do_login()");
        Subject subject = SecurityUtils.getSubject();
        logger.info("rememberMe:"+ rememberMe);

        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
            try {
                subject.login(token);
            } catch (UnknownAccountException ue) {
                logger.error("UnknownAccountException: "+ue.getMessage());
            } catch (IncorrectCredentialsException e){
                logger.error("IncorrectCredentialsException: "+e.getMessage());
            } catch (AuthenticationException ae) {
                ae.printStackTrace();
                logger.error("AuthenticationException: "+ae.getMessage());
//                attr.addFlashAttribute("error", "Invalid Credentials");
                return "redirect:/login";
            }
        }

        return "redirect:/hello";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "index";
    }

    @RequestMapping("/doregister")
    public String doRegister(@RequestParam String username, @RequestParam String password) {
        return "login";
    }

    @GetMapping("/user/info")
    @RequiresPermissions("user:view")
    public String userInfo(Model model) {
        String name = "anonymous";
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            name = principalCollection.getPrimaryPrincipal().toString();
        }
        model.addAttribute("name", name);
        return "userInfo";
    }

    @GetMapping("/user/del")
    @RequiresPermissions("user:del")
    public String userDel(){
        return "userDel";
    }
}
