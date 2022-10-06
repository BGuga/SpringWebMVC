package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller;

import SpringMVC.servlet.domain.member.Member;
import SpringMVC.servlet.domain.member.MemberRepository;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyController;

import java.util.Map;

public class MysaveController implements MyController {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> parameter, Map<String, Object> model) {
        System.out.println("save controller is called");
        String username = parameter.get("username");
        int age = Integer.parseInt(parameter.get("age"));
        Member member = new Member(username, age);
        Member returnMember = memberRepository.save(member);
        model.put("member", returnMember);

        return "save-result";

    }
}
