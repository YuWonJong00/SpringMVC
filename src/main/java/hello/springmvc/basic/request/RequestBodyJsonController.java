package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", s);
        HelloData helloData = objectMapper.readValue(s, HelloData.class);
        log.info("usernae={}, age={}",helloData.getUsername(),helloData.getAge());
        response.getWriter().write("ok");
    }
}
