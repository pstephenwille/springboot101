package springboot101;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stephen on 3/3/15.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "greeting from spring";
    }


}
