package bluish_Community_Project.bluish.beanfind;

import bluish_Community_Project.bluish.member.MemberRepository;
import bluish_Community_Project.bluish.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanTest.class);

    @Test
    @DisplayName("타입으로 조회시 같은 값이 둘 있는 경우 오류 발생 유도 테스트")
    public void findBeanByTypeDuplicate() throws Exception{
        assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있을 경우 명시적 이름을 지정하여 조회 가능하다")
    public void findBeanByName() throws Exception{
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        Assertions.assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입으로 모두 조회하기")
    public void finAllBeanByName() throws Exception{
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "Value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class sameBeanTest{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
