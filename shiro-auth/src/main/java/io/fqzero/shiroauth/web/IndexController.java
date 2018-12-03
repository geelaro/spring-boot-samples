package io.fqzero.shiroauth.web;

import org.apache.shiro.SecurityUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

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
//        Subject subject = SecurityUtils.getSubject();
//
//        if (subject!=null&&subject.isAuthenticated()){
//            return "redirect:/";
//        }

        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request,Model model){
        String name = "World";

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
            if (CollectionUtils.isEmpty(principalMaps)) {
                name = subject.getPrincipal().toString();
            }
            else {
                name = (String) principalMaps.iterator().next().get("username");
            }
        }

        model.addAttribute("name", name);

        return "hello";
    }

    @GetMapping("/403")
    public String fail403(){
        return "403";
    }

//    @GetMapping("/error")
//    public String error(HttpServletRequest request, Model model){
//        Map<String,Object> errorMap = errorAttributes.getErrorAttributes((WebRequest) request,false);
//        model.addAttribute("errors",errorMap);
//        return "error";
//    }

}
