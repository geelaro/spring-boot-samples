package io.fqzero.shiroauth.web;

import io.fqzero.shiroauth.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

@Controller
public class IndexController {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequiresGuest
    @GetMapping({"/","/index"})
    public String index(){

        return "index";
    }


    @RequestMapping("/login")
    public String login(){
        logger.info("login()");

        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request,Model model){
        Object name = "World";

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
            if (CollectionUtils.isEmpty(principalMaps)) {
                User user= (User)subject.getPrincipal();
                name = user.getUsername();
            }
            else {
                name = (String) principalMaps.iterator().next().get("username");
            }
        }

        model.addAttribute("name", name);

        return "hello";
    }

    @GetMapping("/art")
    @RequiresAuthentication
    public String art(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "art";
        } else return "401";
    }

    @GetMapping("/401")
    public String fail403(){
        return "401";
    }


}
