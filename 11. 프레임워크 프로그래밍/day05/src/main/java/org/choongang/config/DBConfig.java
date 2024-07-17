package org.choongang.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("org.choongang.member.mappers")
@EnableJdbcRepositories("org.choongang")

public class DBConfig extends AbstractJdbcConfiguration {
    @Bean(destroyMethod = "close") // 자원 해제(스프링 컨테이너가 사라질 때 같이 소멸)
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        
        /* DB 연결 설정 S */
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        ds.setUsername(System.getenv("db.username"));
        ds.setPassword(System.getenv("db.password"));
        /* DB 연결 설정 E */

        /* 커넥션 풀 설정 S */
        ds.setTestWhileIdle(true); // 유휴 객체 유효성 체크(연결이 유효한지 아닌지)
        ds.setInitialSize(2);
        ds.setMaxActive(10); // 10개를 넘어가지 않음
        ds.setMinEvictableIdleTimeMillis(1000 * 60); // 유휴 객체 생존 시간 1분(기본 설정이 1분)
        ds.setTimeBetweenEvictionRunsMillis(1000 * 5); // 5초에 한번씩 연결 상태 체크(설정을 따로 하지 않으면 5초)
        /* 커넥션 풀 설정 E */

        return ds;
    }

    @Bean // jdbcTemplate을 컨테이너에 관리객체로 넣어준다
    // 외부자원?은 수동으로 등록해야 한다
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        return factoryBean.getObject();
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}