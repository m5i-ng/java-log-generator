package com.example.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SellRequest implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SellRequest.class);
    private final long id;

    public SellRequest(final long id) {
        this.id = id;
    }

    public void randomDelay(boolean running, int min, int max) {
        int random = (int)Math.floor(Math.random()*(max-min+1)+min);
        int random_seconds = random * 1000;
        //log.info("Random value in int from "+min+" to "+max+ " : "+random);
        //log.info("Random seconds: "+random_seconds);
        // to create unhandled exception if running is set to false            
        try {
            Thread.sleep(random_seconds);        
            } catch (InterruptedException e) {
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
            log.info("{}", Utils.getRandomSell(id));
            randomDelay(true, 1, 3);
        } else if (id % 2 == 0) {
            log.warn("{}", Utils.getRandomSell(id));
            randomDelay(true, 3, 5);
        }  else {
            log.error("{}", Utils.getRandomSell(id));
            randomDelay(false, 5, 8);
        }
    }
}