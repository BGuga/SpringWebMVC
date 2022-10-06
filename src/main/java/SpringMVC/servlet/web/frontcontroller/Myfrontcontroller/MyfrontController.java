package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller;

import SpringMVC.servlet.web.frontcontroller.MyView;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyController;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller.MyListController;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller.MyformController;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller.MysaveController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "myfrontController", urlPatterns = "/my-controller/*")
public class MyfrontController extends HttpServlet {
    private Map<String, MyController> controllerMap = new HashMap<>();

    public MyfrontController() {
        controllerMap.put("/my-controller/members/new-form", new MyformController());
        controllerMap.put("/my-controller/members", new MyListController());
        controllerMap.put("/my-controller/members/save", new MysaveController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        MyController controller = controllerMap.get(requestURI);
        if (controller==null){
            resp.setStatus(404);
            return;
        }
        Map<String, String> parametermap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining((parameter)->{
            parametermap.put(parameter, req.getParameter(parameter));
        });


        Map<String, Object> model = new HashMap<>();
        String process = controller.process(parametermap, model);

        MyView myView = myViewresolver(process);

        myView.render(model,req,resp);
    }

    private static MyView myViewresolver(String process) {
        return new MyView("/WEB-INF/views/" + process + ".jsp");
    }
}
