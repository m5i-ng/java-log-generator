package com.example.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchRequest implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SearchRequest.class);
    private final long id;

    public SearchRequest(final long id) {
        this.id = id;
    }

    public void randomDelay(boolean running, int min, int max){
        int random = (int)Math.floor(Math.random()*(max-min+1)+min);
        //int random_seconds = random * 1000 * 60;
        int random_seconds = random * 1000;
        //log.info("Random value in int from "+min+" to "+max+ " : "+random);
        //log.info("Random seconds: "+random_seconds);
        try {
             Thread.sleep(random_seconds);        
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!running) {
            int result = 5/0;
            System.out.println("result "+result);
        }
    }

    @Override
    public void run() {
        if (id % 3 == 0) {
            log.info("{}", Utils.getRandomSearch(id));
            for (int j = 0; j < 2; j++) {
                //log.info("Entering Loop: run "+j);
                randomDelay(true ,1, 2);
            }
         } else if (id % 2 == 0) {
            log.warn("{}", Utils.getRandomSearch(id));
            randomDelay(true, 3, 8);
         }  else {
            log.error("{}", Utils.getRandomSearch(id));
            randomDelay(false, 5, 9);
         }
    }
}
