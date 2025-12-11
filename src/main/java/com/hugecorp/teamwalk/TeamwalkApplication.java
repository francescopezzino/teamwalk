package com.hugecorp.teamwalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeamwalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamwalkApplication.class, args);
	}

}
