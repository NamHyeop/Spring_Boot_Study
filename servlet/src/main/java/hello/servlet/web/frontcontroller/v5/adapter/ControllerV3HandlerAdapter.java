package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        //handler값이 ControllerV3이면 참 아니면 거짓
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletResponse request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
