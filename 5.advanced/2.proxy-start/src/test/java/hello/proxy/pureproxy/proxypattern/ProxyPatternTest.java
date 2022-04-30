package hello.proxy.pureproxy.proxypattern;

import hello.proxy.pureproxy.proxypattern.code.CacheProxy;
import hello.proxy.pureproxy.proxypattern.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxypattern.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    public void noProxyTest() throws Exception{
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    /**
     * 캐쉬 프록시는 데이터가 존재하면 바로 반환하기 때문에 훨씬 더 빠르다.
     * @throws Exception
     */
    @Test
    public void cacheProxyTest() throws Exception{
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
