package me.wane.jpa;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.wane.jpa.hibernate_jpa.main.JpaMain;
import me.wane.jpa.hibernate_jpa.merge.MergeMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class JpaApplication {

	private final JpaMain jpaMain;
	private final MergeMain mergeMain;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@PostConstruct
	public void init() {
//		jpaMain.main();
//		mergeMain.mergeMain();
	}
}
