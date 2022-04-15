package bluish_Community_Project.bluish;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * basePackges를 지정해서 제외할 영역 지정 가능
         */
//        basePackages = "bluish_Community_Project.bluish",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class
        ))
public class AutoAppConfig {
}
