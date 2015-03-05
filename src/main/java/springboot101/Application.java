package springboot101;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/* todo:

The NY Times and Hacker News both have APIs you can use to get the current top stories. I want you to build a web
service with an endpoint that returns up to 10 top stories (up to 20 total) from each source in the following format:
HackerNews: https://github.com/HackerNews/API

    [{
        "title": "The First Top Story",
        "url": "http://alinktothestory.com",
        "source": "nyt"
    },{
        "title": "The Second Top Story",
        "url": "http://alinktothestory.com",
        "source": "hn"
    }];

 */
/*
* return top 10 items
*
* make request for top 500 item IDs - https://hacker-news.firebaseio.com/v0/topstories.json
*
* get items by ID - https://hacker-news.firebaseio.com/v0/item/9139817.json
* evaluate score and keep only the first 10 with a score of 1
*
* take the first 10 and build out json obj to return.
*
* return json objects of top ten items
*
* make item DAO
* testing
* * * */
@ComponentScan(basePackages = {"springboot101"})
@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    HackerNewsService hackerNewsService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run");
//        hackerNewsService.getTopStories();
    }


}
