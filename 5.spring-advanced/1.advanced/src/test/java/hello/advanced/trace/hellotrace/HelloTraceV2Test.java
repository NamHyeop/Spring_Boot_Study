package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV2;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {
    @Test
    public void begin_end_level2() throws Exception{
        //given
        HelloTraceV2 trace = new HelloTraceV2();
        //when
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
        //then
        trace.end(status2);
        trace.end(status1);
    }
    @Test
    public void begin_exception_level2() throws Exception{
        //given
        HelloTraceV2 trace = new HelloTraceV2();
        //when
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
        //then
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
    }
}