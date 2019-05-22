package com.geelaro.jpa.controller;

import com.geelaro.jpa.domain.entity.IpAddress;
import com.geelaro.jpa.domain.entity.User;
import com.geelaro.jpa.domain.repository.IpRepository;
import com.geelaro.jpa.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.toString());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IpRepository ipRepository;

    @ResponseBody
    @RequestMapping("/")
    public String index(HttpServletRequest request) {

        String ip = getRealIp(request);
        logger.info("IP:" + ip);
        IpAddress ipAddress = ipRepository.findByIp(ip);

        if (ipAddress == null) {
            ipAddress = new IpAddress();
            ipAddress.setIp(ip);
            ipAddress.setTimes(1);
        } else {
            ipAddress.setTimes(ipAddress.getTimes() + 1);
        }

        ipRepository.save(ipAddress);
        return "I have been seen " + ip + " " + ipAddress.getTimes() + " times";
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, List> getUserList(@RequestParam Integer page, @RequestParam Integer count) {
        Map<String, List> map = new LinkedHashMap<>();

        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, count, sort);
        Page<User> pages = userRepository.findAll(pageable);

        int counts = (int) pages.getTotalElements();
        List<User> list = pages.getContent();
        map.put("total", Arrays.asList("" + counts));
        map.put("count", Arrays.asList("" + count));
        map.put("subjects:", list);

        logger.info("number: " + pages.getNumber());
        logger.info("Num Of elements:" + pages.getNumberOfElements());
        logger.info("Size: " + pages.getSize());

        return map;
    }

    /**
     * real ip
     *
     * @param request
     * @return
     */
    private String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
