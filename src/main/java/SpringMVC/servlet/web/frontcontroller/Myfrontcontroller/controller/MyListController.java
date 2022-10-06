package SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.controller;

import SpringMVC.servlet.domain.member.Member;
import SpringMVC.servlet.domain.member.MemberRepository;
import SpringMVC.servlet.web.frontcontroller.Myfrontcontroller.MyController;

import java.util.List;
import java.util.Map;

public class MyListController implements MyController {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> parameter, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);

        return "members";
    }
}
