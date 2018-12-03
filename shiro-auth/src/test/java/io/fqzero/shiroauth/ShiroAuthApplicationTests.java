package io.fqzero.shiroauth;

import io.fqzero.shiroauth.entity.Role;
import io.fqzero.shiroauth.entity.User;
import io.fqzero.shiroauth.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroAuthApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		String algorithmName = "md5";
		//盐（用户名+随机数）
		String username = "admin";
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		//原密码
		String password = "123456";
		//散列的次数
		int hashIterations = 2;
		//进行散列获取匹配值
		SimpleHash hash = new SimpleHash(algorithmName, password, username + salt, hashIterations);

		String encodedPassword = hash.toHex();

		System.out.println("这个是保存进数据库的密码:"+encodedPassword);

		System.out.println("这个是保存进数据库的盐:"+salt);
	}


	@Test
	public void testUser(){

		userService.addUser("alice","alice");
	}

	@Test
	public void testAdmin(){
		User user = userService.findByUsername("alice");

//		user.getRoleList().forEach(role -> role.getPermissions().forEach( permission -> permission.));
		System.out.println(user.toString());
		user.getRoleList().forEach(role -> {System.out.println(role.getRole());});

	}

	@Test
	public void debug(){

		System.out.println("Mem: "+getMemUsage());
	}


	public static String getMemUsage() {
		long free = java.lang.Runtime.getRuntime().freeMemory();
		long total = java.lang.Runtime.getRuntime().totalMemory();
		StringBuffer buf = new StringBuffer();
		buf.append("[Mem: used ").append((total - free) >> 20)
				.append("M free ").append(free >> 20)
				.append("M total ").append(total >> 20).append("M]");
		return buf.toString();
	}



}
