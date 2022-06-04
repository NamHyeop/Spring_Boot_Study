package hello.advanced.trace.logTrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    //TraceId 동기화를 위한 필드, (주의)동시성 이슈가 발생할 수 있다.
    //private TraceId traceHolder;
    private ThreadLocal<TraceId> traceHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceHolder.get();
        Long startTimes = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()),message);
        return new TraceStatus(traceId, startTimes, message);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status,null);
    }

    @Override
    public void exception(TraceStatus status, Exception e){
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e){
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if(e == null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        }else{
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
        releaseTraceId();
    }

    private void syncTraceId(){
        TraceId traceId = traceHolder.get();
        if(traceId == null){
            traceHolder.set(new TraceId());
        }else{
            traceHolder.set(traceId.createNextId());
        }
    }

    private void releaseTraceId(){
        TraceId traceId = traceHolder.get();
        if(traceId.isFirstLevel()){
            traceHolder.remove(); //스레드로컬의 스레드 값 자체를 지워버리는것
        }else{
            traceHolder.set(traceId.createPreviousId());
        }
    }

    private static String addSpace(String prefix, int level){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < level; i++){
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
