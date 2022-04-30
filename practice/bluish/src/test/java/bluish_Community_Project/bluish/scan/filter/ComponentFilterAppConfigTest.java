package bluish_Community_Project.bluish.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {
    @Test
    public void filterScan() throws Exception{
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        //when
        BeanA beanA = ac.getBean("beanA", BeanA.class);
//        BeanB beanB = ac.getBean("beanB", BeanB.class); excludeFilters로 인해 존재 x
        assertThat(beanA).isNotNull();
        //then
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("BeanB", BeanB.class));
    }
    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
