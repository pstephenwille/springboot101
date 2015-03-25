package springboot101;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TopStoriesController {

    @Autowired
    HackerNewsService hn_service;


    /* root */
    @RequestMapping("/")
    public String index() {

        return "hacker news's top stories";
    }


    /* /top-rated*/
    @RequestMapping(
            value = "/top-rated",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public List<Item> getSortedItems() {

        if (hn_service.sortedItems.size() < 1) {

            return hn_service.getSortedStories();
        } else {

            return hn_service.sortedItems;
        }

    }

}



