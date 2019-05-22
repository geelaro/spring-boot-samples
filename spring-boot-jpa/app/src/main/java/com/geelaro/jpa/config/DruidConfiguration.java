package com.geelaro.jpa.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.toString());

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        //servlet实体
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单，deny级别高于allow
        servletRegistrationBean.addInitParameter("deny", "35.234.21.252");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> statFilter() {
        //创建过滤器
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        //过滤器路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.png,*.css,*.ico,/druid/*");

        return filterRegistrationBean;
    }

}
