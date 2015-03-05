import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import springboot101.HackerNewsService;
import springboot101.Item;
import springboot101.TopStoriesController;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class TopStoriesControllerTest {


    private TopStoriesController controller;

    @Before
    public void setUp() throws Exception {
        HackerNewsService service = mock(HackerNewsService.class);
        when(service.getSortedStories()).thenReturn(Arrays.asList(new Item()));
        controller = new TopStoriesController();
    }


    @Test
    public void testSortedItems2() throws Exception {
    }
}
