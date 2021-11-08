package hello.core.lifecycle;

//콜백 사용법 3가지
//1.스프링 내부의 인터페이스를 정의한 것을 상속하는 방법
//InitailizingBean은 초기화 빈이다. 역할은 의존관계 주입이 끝난뒤 초기화할 것들을 집어넣는 afterPropertiesSet이라는 메소드를 가지고 있다.
//DisposableBean은 종료될때 실행되는 실행할 명령어를 가지는 메소드를 추가하는 인터페이스 역할이다.
//implements InitializingBean, DisposableBean


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String message){
        System.out.println("call = " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init(){
        System.out.println("Netwokrclient.init");
        connect();
        call("초기화 연결 메시지");
    }
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
