package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse
            response) throws IOException { // 예외는 response.getWriter 할 때 필요
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody // @Controller에서 view가 아닌 메세지 자체에 String 보낼 때
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age = {}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // @RequsestParam("") 생략 : 이름 똑같다면 가능
            @RequestParam int age) {
        log.info("username={}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username, // 더 생략 가능, 팀바팀으로 사용 권장
            int age) {
        log.info("username={}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-requried")
    public String requestParamRequired( // required값이 없으면 400 에러 발생
            @RequestParam(required = true) String username, // 단 null vs ""랑 다름, 클라에서 username= 으로만 넘기면 ""으로 넘어와서 400 에러 발생하지 않음
            @RequestParam(required = false) int age) {
//            @RequestParam(required = false) Integer age) { // 기본형에서는 null이 들어갈 수 없기 때문에 500에러 발생, 따라서 Integer으로 받아야 문제 안생김
        log.info("username={}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default") // defaultValue는 "", null 모두 defaultValue 값으로 처리해줌
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map") // 단, param id 하나에 value 2개 일 수 있으니 MultiValueMap 사용 권장
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
//    public String requestParamMap(@RequestParam MultiValueMap<String, Object> paramMap){
        log.info("username={}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { // 생략 가능
        log.info("username={}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
