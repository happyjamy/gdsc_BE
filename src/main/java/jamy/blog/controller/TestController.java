package jamy.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 해당 클래스를 Spring MVC 컨트롤러로 표시
public class TestController {
    @ResponseBody // return 값을 그대로 HTTP Response Body 전문에 담아 클라이언트에게 전달한다.
    @GetMapping("/hello")
// Controller에 들어온 요청을 특정 메서드와 매핑하기 위하여 사용
// value : 요청받을 URL, method : 어떤 메서드의 요청을 받을지
    public String hello() {
        return "hello spring world";
    }
}