package config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // 스프링에서 트랜잭션 관리를 활성화하는 데 사용. 스프링 애플리케이션에서 @Transactional 어노테이션을 사용하여 메서드 단위의 트랜잭션 처리를 설정할 수 있음
@MapperScan("mappers")
// @EnableJdbcRepositories("member") // 데이터베이스와의 연동을 위한 리포지토리 인터페이스를 스프링 데이터 JDBC가 인식하고 관리할 수 있도록 설정
@ComponentScan("member")
public class AppCtx {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        /* DB 연결 설정 S */
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        ds.setUsername("SPRING");
        ds.setPassword("oracle");
        /* DB 연결 설정 E */

        /* 커넥션 풀 설정 S */
        ds.setTestWhileIdle(true); // 유휴 객체 유효성 체크
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000); // 10초에 한번씩 연결 상태 체크
        ds.setMinEvictableIdleTimeMillis(1000 * 60); // 유휴 객체 생존 시간 1분 -> 시간이 지나면 소멸되고 다시 만들어진다
        /* 커넥션 풀 설정 E */

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate () {
        return new JdbcTemplate(dataSource()); // 객체를 조립해서 완성된 jdbc템플릿
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());

        return tm;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        return sqlSessionFactory;
    }
}
