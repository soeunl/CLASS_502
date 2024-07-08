package config;

import board.services.BoardService2;
import global.annotations.ManualBean;
import member.dao.MemberDao;
import member.validators.JoinValidator;
import org.springframework.context.annotation.*;

/*
@ComponentScan(basePackages="member",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes= ManualBean.class)
) */

/*
@ComponentScan(basePackages = "member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {MemberDao.class, JoinValidator.class})
) */

/*
@ComponentScan(basePackages = "member",
excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "member\\.*\\w*Dao")
)*/

/*
@ComponentScan(basePackages = {"member","board"},
excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "member..*Dao")
)*/

@Configuration
@ComponentScan({"member", "board"})
public class AppCtx {

    @Scope("prototype") // 매번 새로운 객체를 생성. 기능이 조금 다르게 동작한다
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BoardService2 boardService2() {
        return new BoardService2();
    }
}
