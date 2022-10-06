package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller;

import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyController;

import java.util.Map;

public class MyformController implements MyController {

    @Override
    public String process(Map<String, String> parameter, Map<String, Object> model) {
        return "new-form";
    }
}
