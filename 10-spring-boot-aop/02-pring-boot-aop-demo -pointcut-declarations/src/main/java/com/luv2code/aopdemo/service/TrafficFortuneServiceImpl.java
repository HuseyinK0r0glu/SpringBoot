package com.luv2code.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService{

    @Override
    public String getFortune(boolean tripWire) {
        if(tripWire){
            throw new RuntimeException("Major accident,Highway is closed!");
        }

        return getFortune();
    }

    @Override
    public String getFortune() {

        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // return the fortune
        return "Expect heavy traffic this morning";
    }
}
