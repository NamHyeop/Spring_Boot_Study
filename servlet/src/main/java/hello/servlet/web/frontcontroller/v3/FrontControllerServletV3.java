package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //request.getRequestURI로 URL를 그대로 받안온다.
        String requestURI = request.getRequestURI();
        //requesturl받아서 3개중 하나의 객체 종류를 받아온다.
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //속성 값을 넣은 map을 만든뒤 위에서 받아온 개체에 넣어준다.
        Map<String, String> paraMap = createParamMap(request);
        ModelView mv = controller.process(paraMap);

        //위에 다향성으로 설정된 이름을 활용하여 view resolver를 실행해준다.
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        //paraMap을 넘겨줘야 한다.
        Map<String, String> paraMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paraName -> paraMap.put(paraName, request.getParameter(paraName)));
        return paraMap;
    }
}

