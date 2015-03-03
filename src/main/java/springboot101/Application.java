package springboot101;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

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

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {
    List<Item> topItems = new ArrayList();
    List<Future<Item>> futureList = new ArrayList();
    List<Item> sortedItems = new ArrayList();

    public static void main(String[] args) {
//        ApplicationContext ctx = SpringApplication.run(Application.class, args);
//
//        System.out.println("beans");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//
//        for (String beanName : beanNames) {
//            System.out.println("bean names " + beanName);
//
//        }

        //fbservice
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    HackerNewsService hackerNewsService;

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();

        //run multiple
//        Future<Item> page1 = hackerNewsService.getItem("9127054");
//        Future<Item> page2 = hackerNewsService.getItem("9134903");
//        Future<Item> page3 = hackerNewsService.getItem("9123822");

        //wait
//        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
//            Thread.sleep(10);//10ms pause between each check.
//        }

//        System.out.println(page1.get());
//        System.out.println(page2.get());
//        System.out.println(page3.get());


        hackerNewsService.topStories().forEach((Object id) -> {
            try {

                futureList.add(hackerNewsService.getItem(id));

            } catch (InterruptedException e) {
                System.out.println("interrupted " + e);
            }
        });


        futureList.forEach((Future item) -> {
            try {

                topItems.add((Item) item.get());

            } catch (Exception e) {
                System.out.println("topitems " + e);

            }
        });

        System.out.println("END ............ " + topItems.size());


        topItems.stream()
                .filter((itemA, itemB) -> {
                    Item _bigger = (itemA.getScore() < itemB.getScore())
                            ? itemB
                            : itemA;
                    

                });

    }

}
