package bluish_Community_Project.bluish.beanfind;

import bluish_Community_Project.bluish.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 조회하기")
    public void findAllBean() throws Exception {
        //given
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //when
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            //then
            System.out.println("beanDefinitionName = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("사용자추가 어플리케이션 빈 조회하기")
    public void findApplicationBean() throws Exception {
        //given
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //when
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //then
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object= " + bean);
            }
        }
    }
}
