package com.cn.sh.lilac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author gxx
 */

@SpringBootApplication
@EnableScheduling
@MapperScan("com.cn.sh.lilac.dao")
public class MewApplication {

	public static void main(String[] args) {

		SpringApplication.run(MewApplication.class, args);
	}

}
