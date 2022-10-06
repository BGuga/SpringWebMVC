package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller;

import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyController;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller.MyformController;

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
        controllerMap.put("/my-controller/members", new MyformController());
        controllerMap.put("/my-controller/members/save", new MyformController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
