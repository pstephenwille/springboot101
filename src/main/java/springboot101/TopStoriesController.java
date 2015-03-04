package springboot101;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by stephen on 3/3/15.
 */
@RestController
public class TopStoriesController {


    @Autowired
    HackerNewsService hackerNewsService;


    /* root */
    @RequestMapping("/")
    public String index() {
        return "hacker news's top stories";
    }


    /* topstories */
    @RequestMapping(
            value = "/toprated",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public List<Item> getSortedItems() {
        return hackerNewsService.getSortedStories();
    }
}



