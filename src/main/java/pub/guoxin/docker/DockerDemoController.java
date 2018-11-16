package pub.guoxin.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by guoxin on 2018/11/16
 */
@RestController
public class DockerDemoController {

    @GetMapping("/docker-demo")
    public String helloWord() {
        return "hello word";
    }
}
