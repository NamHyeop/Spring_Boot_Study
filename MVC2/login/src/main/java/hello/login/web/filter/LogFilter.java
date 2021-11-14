package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");
        //ServletRequest는 HttpServletRequest의 부모인데 ServletRequest의 기능은 너무 적기 때문에 HttpServletRequest로 캐스팅 해줘야한다.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();


        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            //chain은 filter과정이 더 남아있으면 더 남은 과정으로 이동하고 아닐경우 servlet과정을 진행한다. 현재 스프링을 사용하므로 정확히는 DispatchServlet으로 넘어가서 mapping과정을 진행한다.
            chain.doFilter(request, response);
        }catch (Exception e){
            throw  e;
        }finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        System.out.println("log filter destroy");
    }
}
