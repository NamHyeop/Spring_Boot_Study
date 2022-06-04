package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {
    private final OrderControllerV1 target;
    private final LogTrace logTrace;


    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try{
            status = logTrace.begin("OrderController.request");
            //target 호출
            String result = target.request(itemId);
            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

    /**
     * log가 필요한 경우 재정의
     * 현재로는 요구사항으로 log가 없는 상황
     * @return
     */
    @Override
    public String noLog() {
        return target.noLog();
    }
}
