import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import springboot101.HackerNewsService;
import springboot101.TopStoriesController;

import java.util.ArrayList;

@RunWith(JMockit.class)
public class TopStoriesControllerTest {


//    private TopStoriesController controller;

//    @Before
//    public void setUp() throws Exception {
//        HackerNewsService service = mock(HackerNewsService.class);
//        when(service.getSortedStories()).thenReturn(Arrays.asList(new Item()));
//        controller = new TopStoriesController();
//    }

    @Mocked(stubOutClassInitialization = true)
    TopStoriesController ctlr;

    @Mocked
    HackerNewsService srvc;


    @Test
    public void getSortedItems() {

        new Expectations() {{
            ctlr.getSortedItems();
            result = "";
            srvc.getSortedStories();
            result = new ArrayList<>();

        }};

        ctlr.getSortedItems();
        srvc.getSortedStories();

    }


}
