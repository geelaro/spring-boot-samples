package io.fqzero.shiroauth.service;

import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SqlStatement;
import io.fqzero.shiroauth.entity.Role;
import io.fqzero.shiroauth.entity.User;
import io.fqzero.shiroauth.repository.UserRepository;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Transactional
    public User addUser(String username, String password) {

        User addUser = encrypt(username,password);
        addUser.setUid(new Random().nextInt());
        return userRepository.save(addUser);
    }


    /**
     * MD5加密
     */
    private User encrypt(String username, String password) {
        String algorithmName = "md5";
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        //MD5次数
        int hashIterations = 2;
        //进行散列获取匹配值
        SimpleHash hash = new SimpleHash(algorithmName, password, username + salt, hashIterations);
        //md5后的密码
        String encryptPassword = hash.toHex();

        User user = new User();

        user.setSalt(salt);
        user.setUsername(username);
        user.setPassword(encryptPassword);

        return user;
    }


}
