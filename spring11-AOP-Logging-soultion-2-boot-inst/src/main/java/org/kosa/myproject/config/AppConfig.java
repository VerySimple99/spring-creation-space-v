package org.kosa.myproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("org.kosta.myproject")
@EnableAspectJAutoProxy
public class AppConfig {

}
