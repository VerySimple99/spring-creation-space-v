package org.kosta.myproject.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // 스프링 컨테이너에 스프링 설정 클래스임을 알린다 
@ComponentScan("org.kosta.myproject")  // bean 생성 관리 및 DI 
@MapperScan("org.kosta.myproject.model") // MyBatis @Mapper 명시 인터페이스를 구현하는 Proxy 클래스 자동 생성을 위한 설정 
public class AppConfig {
	//아래는 xml 설정 <bean class="javax.sql.DataSource" id="dataSource"></bean> 와 같은 역할
	@Bean // 리턴하는 객체를 bean 으로 생성 관리 ( bean name 은 메서드명 ) 	
	public DataSource dataSource() {
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@13.124.215.95:1521:xe");
		dataSource.setUsername("scott");
		dataSource.setPassword("tiger");
		return dataSource;
	}
	@Bean // spring container가 bean 으로 생성시 dataSource를 매개변수로 전달
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		// 위에서 생성한 dbcp를 setting 
		sqlSessionFactoryBean.setDataSource(dataSource);
		//아래 설정은 Mapper Proxy 를 이용하므로 필요하지 않다
		//( Mapper Interface와 Mapper xml 은 동일한 경로에 있어야 한다 )
		// MyBatis 설정 파일 위치를 알려 MyBatis 설정을 로딩하게 한다 
		//Resource []res=new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*Mapper.xml");
		//sqlSessionFactoryBean.setMapperLocations(res);
		/*
		 *  db column  underscore 합성어를 구성 ( ex - account_no ) , java 는 camel case로 합성어를 구성 (  ex - accountNo )
		 *  이 둘을 자동 매칭시켜주는 설정  
		 */
		org.apache.ibatis.session.Configuration conf=new org.apache.ibatis.session.Configuration();
		conf.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(conf);
		
		return sqlSessionFactoryBean.getObject();
	}
	@Bean  // SqlSessionTemplate : 반복적인 코드 줄이고 생산성 있는 개발 가능하게 한다. AOP 적용 선언적 방식의 트랜잭션 처리를 지원
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}















