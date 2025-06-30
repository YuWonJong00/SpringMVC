package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면,view 조회를 하지 않는다.
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) throws Exception {
        log.info("memberName={}, memberAge={}", memberName, memberAge);
        return "ok";
    }

    /**
     * HTTP 파라미터 이름이 변수 이름과 같으면  @RequestParam(name="xx") 생략 가능
     */
    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) throws Exception {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 파라미터 필수 여부
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 기본값 적용
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * multiValueMap(key=[value1, value2 ...] 하나의 키에  value값이 배열로 여러개 가질 수 있음
     */
    @ResponseBody
    @RequestMapping("/requeset-param-map")
    public String requesetParamMap(
            @RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}

