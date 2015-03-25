package springboot101;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


@Service
public class HackerNewsProvider {

    RestTemplate restTemplate = new RestTemplate();


    public List<Item> getTopStoryIds() {
        ArrayList items = restTemplate
                .getForEntity("https://hacker-news.firebaseio.com/v0/topstories.json", ArrayList.class)
                .getBody();

        return items.subList(0, 250);
    }


    @Async
    public Future<Item> getFutureItem(Object id) throws InterruptedException {
        Item result = restTemplate.getForObject(
                "https://hacker-news.firebaseio.com/v0/item/" + id.toString() + ".json",
                Item.class);

        return new AsyncResult<Item>(result);
    }


}
