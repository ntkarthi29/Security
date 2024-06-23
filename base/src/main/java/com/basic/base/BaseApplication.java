package com.basic.base;

import com.basic.base.enums.ERole;
import com.basic.base.model.User;
import com.basic.base.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BaseApplication  {
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

//	public void run(String... args) throws Exception {
//		User adminAccount = userRepository.findByRole(ERole.ADMIN);
//		if(null == adminAccount){
//			User user = new User();
//			user.setUserName("admin");
//			user.setEmail("admin@gmail.com");
//			user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
//			user.setRole(ERole.ADMIN);
//			userRepository.save(user);
//		}

	}
