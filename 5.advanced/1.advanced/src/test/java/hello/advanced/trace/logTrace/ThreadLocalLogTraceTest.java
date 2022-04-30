package hello.advanced.trace.logTrace;

import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    public void begin_end_levl2() throws Exception{
        //given
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        //when
        //then
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    public void begin_exception_level2() throws Exception{
        //given
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        //when
        //then
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
    }
}