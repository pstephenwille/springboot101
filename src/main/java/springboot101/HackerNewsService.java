package springboot101;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


@Service
public class HackerNewsService {
    List<Item> topItems = new ArrayList();
    List<Item> topItemsData = new ArrayList();
    List<Future<Item>> futureList = new ArrayList();
    List<Item> sortedItems = new ArrayList();

    RestTemplate restTemplate = new RestTemplate();


    public List<Item> getTopStories() {
        ArrayList items = restTemplate
                .getForEntity("https://hacker-news.firebaseio.com/v0/topstories.json", ArrayList.class)
                .getBody();

//         topItems = items; /* all 500 items */
        topItems = items.subList(0, 50);

        return topItems;
    }


    @Async
    public Future<Item> getItem(Object id) throws InterruptedException {
        Item result = restTemplate.getForObject(
                "https://hacker-news.firebaseio.com/v0/item/" + id.toString() + ".json",
                Item.class);

        return new AsyncResult<Item>(result);
    }


    public List<Item> getSortedStories() {
        System.out.println("real getSortedStories");
        /* get each item */
        topItems.forEach((Object id) -> {
            try {

                futureList.add(getItem(id));

            } catch (InterruptedException e) {
                System.out.println("interrupted " + e);
            }
        });


        /* resolve item data */
        futureList.forEach((Future item) -> {
            try {

                topItemsData.add((Item) item.get());

            } catch (Exception e) {
                System.out.println("topitems " + e);

            }
        });


        /* sort descending */
        topItemsData.stream()
                .sorted((itemA, itemB) -> Integer.compare(itemA.getScore(), itemB.getScore()))
                .forEach(item -> sortedItems.add(item));


        sortedItems.forEach(item -> System.out.println("sorted " + item.getScore()));

        return sortedItems;
    }
}
