package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonService {

    //자기 자신을 내부로 가지며 static으로 선언하여 딱 한개의 객체만 가지게끔 선언
    //딱 1개의 객체 인스턴스만 존재해야 하므로 생성자를 private으로 막아서 외부에서 new 키워드로 객체 인스턴스가 생성되는것을 막는다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //같은 객체를 반환하는것을 볼 수 있다.
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        /**
         * isSameAs : 객체의 메모리 주소를 비교
         * isEquals : 객체의 값을 비교
         */
        assertThat(singletonService1).isSameAs(singletonService2);
    }
}
