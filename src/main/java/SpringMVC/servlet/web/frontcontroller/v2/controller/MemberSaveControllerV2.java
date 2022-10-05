package SpringMVC.servlet.web.frontcontroller.v2.controller;

import SpringMVC.servlet.domain.member.Member;
import SpringMVC.servlet.domain.member.MemberRepository;
import SpringMVC.servlet.web.frontcontroller.MyView;
import SpringMVC.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age1 = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age1);
        memberRepository.save(member);

        //Model의 데이터를 보관한다
        request.setAttribute("member",member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
