package unina.webrtc.spring.webrtc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WebController {
    @GetMapping("/")
    public String index() {
        return "Hello, WebRTC class! Congrats on your first controller with Spring Boot.";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "WebRTC World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/subscribe-Get")
    public String queryString(@RequestParam Map<String, String> allRequestParams) {
        return String.format("Here is the query string %s", allRequestParams);
    }

    @GetMapping("/subscribe-Post")
    public String payloadParams(@RequestParam Map<String, String> allRequestParams) {
        return String.format("Here are the query parameters within request body %s", allRequestParams);
    }
}
