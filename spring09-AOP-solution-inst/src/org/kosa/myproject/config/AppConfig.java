package org.kosa.myproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("org.kosa.myproject")
@EnableAspectJAutoProxy // Annotation 기반 AOP 설정 
public class AppConfig {
	// dbcp 등을 설정 
}
