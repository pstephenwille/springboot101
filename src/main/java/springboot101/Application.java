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
*
*
* [9262538, 9263082, 9263193, 9262327, 9263107, 9263146, 9262260, 9263532, 9263336, 9262656, 9262949, 9263515, 9262846, 9262428, 9261650, 9261413, 9261073, 9260658, 9261715, 9263715, 9261112, 9260286, 9261399, 9262209, 9261169, 9260963, 9259986, 9260626, 9260169, 9261031, 9260221, 9259813, 9256844, 9262601, 9262543, 9263747, 9263344, 9260087, 9256014, 9260753, 9257818, 9261033, 9262910, 9256322, 9262700, 9262367, 9259292, 9261311, 9258219, 9259790, 9262830, 9260786, 9262575, 9257895, 9263206, 9263020, 9260122, 9259404, 9261253, 9256152, 9254876, 9257591, 9258798, 9257894, 9259821, 9256209, 9263174, 9262592, 9255479, 9262682, 9260293, 9255305, 9261349, 9257130, 9263047, 9258503, 9256415, 9261289, 9262229, 9259757, 9262588, 9259048, 9252808, 9257688, 9254993, 9262230, 9262310, 9262478, 9258181, 9259363, 9263465, 9262940, 9261834, 9255145, 9250314, 9261441, 9261925, 9261423, 9251733, 9256803, 9259910, 9258699, 9257821, 9258541, 9260766, 9254748, 9256827, 9260861, 9255275, 9259403, 9256481, 9256824, 9257926, 9258957, 9259364, 9259852, 9259919, 9258774, 9256939, 9260725, 9256082, 9257033, 9262265, 9258556, 9262190, 9256076, 9257638, 9257450, 9256019, 9258218, 9254947, 9257299, 9258489, 9258810, 9261450, 9256187, 9257761, 9260884, 9259635, 9260436, 9257758, 9259518, 9256283, 9256025, 9256043, 9258533, 9259795, 9250847, 9256049, 9258630, 9255799, 9256486, 9258281, 9257916, 9261907, 9260050, 9258932, 9256103, 9258788, 9257604, 9259690, 9261485, 9260030, 9261850, 9257212, 9261582, 9259178, 9256001, 9256978, 9259088, 9255617, 9256425, 9259148, 9257268, 9260973, 9260965, 9256688, 9256239, 9256815, 9260432, 9258551, 9260667, 9259084, 9258002, 9260795, 9259109, 9257364, 9257725, 9255467, 9255781, 9255861, 9254135, 9257536, 9252920, 9260605, 9259247, 9260394, 9259634, 9259579, 9252999, 9258787, 9257939, 9260145, 9253259, 9257326, 9256342, 9261343, 9250505, 9259624, 9259588, 9259568, 9259412, 9257302, 9255414, 9256995, 9259215, 9261174, 9258614, 9256264, 9259728, 9255383, 9252481, 9259958, 9251577, 9258946, 9254531, 9258667, 9260697, 9254790, 9258211, 9258292, 9254640, 9254092, 9260594, 9253351, 9250208, 9255547, 9242305, 9257583, 9219432, 9243230, 9256661, 9252997, 9185356, 9263046, 9260886, 9234420, 9256159, 9257426, 9256277]
* * * */
@ComponentScan(basePackages = {"springboot101"})
@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Autowired
    HackerNewsService hn_service;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run");
        /* maybe pre fetch story ids: array.size = 500 */
    }


}
