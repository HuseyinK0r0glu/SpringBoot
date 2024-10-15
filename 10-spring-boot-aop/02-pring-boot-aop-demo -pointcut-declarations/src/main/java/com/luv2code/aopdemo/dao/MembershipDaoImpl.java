package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDaoImpl implements MembershipDao{

    @Override
    public boolean addSillyMember() {
        System.out.println(getClass() + " Doing my db work: Adding a membership account");

        return true;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + " I am going to sleep now...");
    }
}
