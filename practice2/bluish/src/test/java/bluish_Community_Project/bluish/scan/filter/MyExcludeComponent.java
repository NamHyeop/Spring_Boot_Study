package bluish_Community_Project.bluish.scan.filter;

import java.lang.annotation.*;

/**
 * @Component에서 가져온것들
 *
 */
@Target(ElementType.TYPE) //Type의 의미는 Class에 사용한다는 의미
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
