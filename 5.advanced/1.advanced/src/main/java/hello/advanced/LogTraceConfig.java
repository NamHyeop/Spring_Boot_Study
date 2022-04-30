package hello.advanced;

import hello.advanced.trace.logTrace.FieldLogTrace;
import hello.advanced.trace.logTrace.LogTrace;
import hello.advanced.trace.logTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace(){
        //동시성 문제 발생 LogTrace 제외
        //return new FieldLogTrace();

        //ThreadLocal로 구현된 동시성 문제 해결이 된 Trace 추가
        return new ThreadLocalLogTrace();
    }
}
