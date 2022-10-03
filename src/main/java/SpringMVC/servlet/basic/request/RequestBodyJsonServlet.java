package SpringMVC.servlet.basic.request;

import SpringMVC.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    //jackson에서 제공하는 object mapper로 json이 될 수 있는 string을 넣어주면 json형식으로 반환해준다
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //body로 들어온 데이터를 byte로 다 가져온다
        ServletInputStream inputStream = request.getInputStream();
        //첫 번째 인자에는 byte코드를 두 번째 인자에는 charset을 넣어줘서 해당 byte코드를 어떻게 해석할 건지 정하고 String을 return한다
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        //만약 messageBody가 Hellodata형식으로 객체가 될 수 있다면 만들어 준다
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("ok");

    }
}
