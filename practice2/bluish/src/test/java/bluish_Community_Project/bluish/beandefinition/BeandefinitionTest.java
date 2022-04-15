package bluish_Community_Project.bluish.beandefinition;

import bluish_Community_Project.bluish.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeandefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈을 이루고 있는 메타데이터 설정 확인 테스트")
    public void findConsistOfFactorBean() throws Exception{
        //given
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //when
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionNAme = " + beanDefinitionName + " beanDefinition " + beanDefinition);
            }
        }
        //then
    }

    /**
     * 빈을 만들어서 사용할시 필요한 빈 메타 정보 매개변수
     */
    static class passiveBeanInfo{
        String bean;
        String scope;
        boolean ab;
        boolean lazyInit;
        Integer autowireMode;
        Integer dependencyCheck;
        boolean autowireCandidate;
        boolean primary;
        String factoryBeanName;
        String factoryMethodName;
        String initMethodName;
        String destroyMethodName;
        String pos; //Config 위치
    }
}
