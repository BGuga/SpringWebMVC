package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller;

import SpringMVC.servlet.web.frontcontroller.MyView;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyController;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.adapter.Myv4Adapter;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.adapter.MyversionAdapter;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller.MyListController;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller.MyformController;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller.MysaveController;
import SpringMVC.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import SpringMVC.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import SpringMVC.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import org.apache.coyote.Adapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "myfrontController", urlPatterns = "/my-controller/*")
public class MyfrontController extends HttpServlet {
    private Map<String, Object> controllerMap = new HashMap<>();
    private List<MyAdapter> adapterMap = new ArrayList<>();

    public MyfrontController() {
        makeControllerMap();
        makeAdapterMap();
    }

    private void makeAdapterMap() {
        adapterMap.add(new MyversionAdapter());
        adapterMap.add(new Myv4Adapter());
    }

    private void makeControllerMap() {
        controllerMap.put("/my-controller/members/new-form/my", new MyformController());
        controllerMap.put("/my-controller/members/my", new MyListController());
        controllerMap.put("/my-controller/members/save/my", new MysaveController());
        controllerMap.put("/my-controller/members/new-form/v4", new MemberFormControllerV4());
        controllerMap.put("/my-controller/members/v4", new MemberListControllerV4());
        controllerMap.put("/my-controller/members/save/v4", new MemberSaveControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        Object controller = controllerMap.get(requestURI);
        if (controller==null){
            resp.setStatus(404);
            return;
        }
        MyAdapter adapter = getAdapter(controller);
        MyView returnview = adapter.process(req, resp, controller);

        returnview.render(req,resp);
    }

    private MyAdapter getAdapter(Object controller){
        for (MyAdapter myAdapter : adapterMap) {
            if(myAdapter.validate(controller)){
                return myAdapter;
            }
        }
        throw new IllegalArgumentException("handler adpater를 찾을 수 없습니다." + controller);
    }

//    private static Map<String, String> getparametermap(HttpServletRequest req) {
//        Map<String, String> parametermap = new HashMap<>();
//        req.getParameterNames().asIterator().forEachRemaining((parameter)->{
//            parametermap.put(parameter, req.getParameter(parameter));
//        });
//        return parametermap;
//    }
//
//    private static MyView myViewresolver(String process) {
//        return new MyView("/WEB-INF/views/" + process + ".jsp");
//    }
}
