package springboot101;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


@Service
public class HackerNewsService {
    @Autowired
    HackerNewsProvider hn_provider;


    List<Item> topItemsData = new ArrayList<>();
    List<Future<Item>> futureList = new ArrayList();
    List sortedItems = new ArrayList();


    public List<Item> getSortedStories() {

        /* get each item */
        hn_provider.getTopStoryIds().forEach((Object id) -> {
            try {

                futureList.add(hn_provider.getFutureItem(id));

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


        System.out.printf("END get sorted stories.... " + topItemsData.size());

        return sortedItems;
    }
}
