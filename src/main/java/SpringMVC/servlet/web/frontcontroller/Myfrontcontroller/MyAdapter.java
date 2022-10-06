package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller;

import SpringMVC.servlet.web.frontcontroller.MyView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyAdapter {
    boolean validate(Object object);
    MyView process(HttpServletRequest request, HttpServletResponse response, Object controller);
}
