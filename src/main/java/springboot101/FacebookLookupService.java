package springboot101;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by stephen on 3/3/15.
 */
@Service
public class FacebookLookupService {

    RestTemplate restTemplate = new RestTemplate();

    @Async 
    public Future<Page> findPage(String page) throws InterruptedException {
        System.out.println("looking up");
        String id = "9139817";
        Page results = restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/"+ id +".json", Page.class);

        Thread.sleep(1000L);

        return new AsyncResult<Page>(results);
    }

}
