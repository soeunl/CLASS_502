package org.choongang.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
public class DBConfig2 {

    // 정적 내부 클래스로 설정하였다.
    // DBConfigDev / DBConfigProd
    // 환경 변수에 따라 빈을 다르게 생성하는 방식! (빈을 설정에 따라 분리해서 사용한다)

    // @Profile은 환경 변수에 따라 생성할 빈을 달리 할 수 있다
    // 환경 변수에 따라 설정 파일을 분리해서 인식 (빈을 설정 파일에 따라 달리 생성)
    // 환경이 모두 다를 수 있기 때문이다 (개발 PC 설정과 배포 PC 설정이 다를 수 있기 때문이다)
    
    @Profile("!prod") // 환경 변수가 prod가 아니면 여기에 있는 빈을 생성한다
    // dev는 스프링 쪽에 이미 정의된 환경 변수
    @Configuration
    @EnableTransactionManagement
    @MapperScan("org.choongang.member.mappers")
    @EnableJdbcRepositories("org.choongang")
    static class DBConfigDev extends AbstractJdbcConfiguration {
        @Bean(destroyMethod = "close") // 자원 해제(스프링 컨테이너가 사라질 때 같이 소멸)
        public DataSource dataSource() {
            log.info("dev 프로파일!");

            DataSource ds = new DataSource();

            /* DB 연결 설정 S */
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
            ds.setUsername("SPRING");
            ds.setPassword("oracle");
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

    @Profile("prod") // 환경 변수가 prod이면 여기에 있는 빈을 생성한다
    // 배포할 때 prod(?)
    @Configuration
    @EnableTransactionManagement
    @MapperScan("org.choongang.member.mappers")
    @EnableJdbcRepositories("org.choongang")
    // 정적 내부 클래스로 설정하였다.
    static class DBConfigProd extends AbstractJdbcConfiguration {
        @Bean(destroyMethod = "close") // 자원 해제(스프링 컨테이너가 사라질 때 같이 소멸)
        public DataSource dataSource() {
            log.info("prod 프로파일!");

            DataSource ds = new DataSource();

            /* DB 연결 설정 S */
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
            ds.setUsername("SPRING");
            ds.setPassword("oracle");
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
}
