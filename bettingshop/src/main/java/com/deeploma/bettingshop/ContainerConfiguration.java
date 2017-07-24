package com.deeploma.bettingshop;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ContainerConfiguration {
	
	@Autowired
	Environment env;
	
	@Autowired
	DataSource ds;
	
	/*@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		ds.setUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));

		ds.setInitialSize(10);
		ds.setMaxActive(5);
		ds.setMaxWait(5);
		ds.setValidationQuery("select 1 from dual");

		

		return ds;
	}*/

	

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(ds);
		//factory.setConfigLocation(new ClassPathResource("my-batis/wallet-config.xml"));
		/*factory.setPlugins(new Interceptor[] {
				queryCommentPlugin(),
				queryTimeoutPlugin()
		});*/
		return factory;
	}
}
