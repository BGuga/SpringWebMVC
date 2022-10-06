package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller;

import SpringMVC.servlet.web.frontcontroller.MyView;

import java.util.Map;

public interface MyController {
    String process(Map<String, String> parameter, Map<String, Object> model);
}
