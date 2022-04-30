package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.helloTrace.HelloTraceV1;
import org.junit.jupiter.api.Test;


class HelloTraceTest {
    @Test
    public void begin_end() throws Exception{
        //given
        HelloTraceV1 trace = new HelloTraceV1();
        //when
        TraceStatus status = trace.begin("hello");
        //then
        trace.end(status);
    }

    @Test
    public void begin_exception() throws Exception{
        //given
        HelloTraceV1 trace = new HelloTraceV1();
        //when
        TraceStatus status = trace.begin("hello");
        //then
        trace.exception(status, new IllegalArgumentException());
    }
}