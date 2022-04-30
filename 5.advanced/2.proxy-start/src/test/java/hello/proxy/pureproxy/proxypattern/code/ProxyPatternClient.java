package hello.proxy.pureproxy.proxypattern.code;

/**
 * Proxy 패턴, Client 요청 클래스
 */
public class ProxyPatternClient {
    private Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        subject.operation();
    }
}
