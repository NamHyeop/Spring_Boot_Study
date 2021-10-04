package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requsetURL;

    public void setRequsetURL(String requsetURL) {
        this.requsetURL = requsetURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requsetURL + "]" + message);
    }

    @PostConstruct
    public void init(){
        //UUID.randonUUID().toString 을 사용하면 전세계에서 유일한 로그 아디가 출력된다. 절대 겹치지 않음
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean create" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("uuid = " + uuid);
        System.out.println("[" + uuid + "]" + "request scope bean close" + this);
    }
}
