package pub.guoxin.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by guoxin on 2018/11/16
 */
@RestController
public class DockerDemoController {

    @GetMapping("/docker-demo")
    public String helloWord() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
