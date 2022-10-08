package SpringMVC.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 아래 둘 @Controller 혹은 @RequestMapping이 있어야 안의 RequestMapping이 작동한다.
//@RequestMapping이 작동할려면 Spring Bean으로 등록 되야하기 때문에 @Component를 같이 쓰던가 수동 Bean등록을 해줘야한다.
//@Controller
@Component
@RequestMapping
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
