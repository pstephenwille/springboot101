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
//        topItems = items.subList(0, 50);

        return items.subList(0, 50);
    }


    @Async
    public Future<Item> getFutureItem(Object id) throws InterruptedException {
        Item result = restTemplate.getForObject(
                "https://hacker-news.firebaseio.com/v0/item/" + id.toString() + ".json",
                Item.class);

        return new AsyncResult<Item>(result);
    }


    public List<Item> getSortedStories() {
        /* nulled out to prevent appending items. */
        topItemsData = new ArrayList<>();
        futureList = new ArrayList();
        sortedItems = new ArrayList();
        
        topItems = getTopStories();
        
        System.out.println("real getSortedStories"+ topItems.size());
        /* get each item */
        topItems.forEach((Object id) -> {
            try {

                futureList.add(getFutureItem(id));

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


        System.out.printf("END get sorted stories.... "+ topItemsData.size());
        
        
        return sortedItems;
    }
}
