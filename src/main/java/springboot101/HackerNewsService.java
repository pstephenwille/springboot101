package springboot101;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by stephen on 3/3/15.
 */
@Service
public class HackerNewsService {

    RestTemplate restTemplate = new RestTemplate();


    public List<String> topStories() {
        ArrayList items = restTemplate
                .getForEntity("https://hacker-news.firebaseio.com/v0/topstories.json", ArrayList.class)
                .getBody();

//        List<String> sub = items; all 500 items
        List<String> sub = items.subList(0, 10);

        return sub;
    }


    @Async
    public Future<Item> getItem(Object id) throws InterruptedException {
        Item result = restTemplate.getForObject(
                "https://hacker-news.firebaseio.com/v0/item/" + id.toString() + ".json",
                Item.class);

        return new AsyncResult<Item>(result);
    }
}
