package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//ComponentSacn은 Spring빈을 전부 검색해서 담는 과정을 수행함, 정확히는 @Componet가 붙은 녀석들을 전부 담아줌
@ComponentScan(
        /** componet scan 범위 설정
        *1.탐색 범위를 지정해주지 않으면 최상단 패키지 기준 하단의 탐색 범위이다. 예제 기준 core
        *2.spring에 포함해줄 componet범위 설정 (구체적 범위 설정)
        *basePackages = "hello.core.member",

        *3.basePackageClasses : 지정한 패기지의 클래스를 탐색 시작 위치로 시작한다 (지정한 패키지 클래스를 탐색 시작 위치로)
        *basePackgeClasses = AutoAppConfig.class,
        */


        //AppConfig에 등록한 Configuration을 제외해주는 과정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
        /**
         * 중복 이름 테스트
         * @Bean(name = "memoryMemberRepository")
         */

//        @Bean(name = "memoryMemberRepository")
//        MemberRepository memberRepository(){
//                return new MemoryMemberRepository();
//        }

}
