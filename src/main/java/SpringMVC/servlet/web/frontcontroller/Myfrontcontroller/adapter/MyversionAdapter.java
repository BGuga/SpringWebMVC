package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.adapter;

import SpringMVC.servlet.web.frontcontroller.MyView;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyAdapter;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyversionAdapter implements MyAdapter {
    @Override
    public boolean validate(Object object) {
        return object instanceof MyController;
    }

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response, Object controller) {
        Map<String, String> parametermap = getparametermap(request);
        MyController handler = (MyController) controller;
        HashMap<String, Object> model = new HashMap<>();
        String result = handler.process(parametermap, model);
        modelToRequestAttribute(model, request);
        return myViewresolver(result);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }

    private static MyView myViewresolver(String process) {
        return new MyView("/WEB-INF/views/" + process + ".jsp");
    }


    private static Map<String, String> getparametermap(HttpServletRequest req) {
        Map<String, String> parametermap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining((parameter)->{
            parametermap.put(parameter, req.getParameter(parameter));
        });
        return parametermap;
    }
}
