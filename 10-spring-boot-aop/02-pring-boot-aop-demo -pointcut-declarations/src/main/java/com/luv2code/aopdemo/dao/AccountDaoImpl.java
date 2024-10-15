package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao{

    private String name;

    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        // simulate an exception
        if(tripWire){
            throw new RuntimeException("No Soup for you!!");
        }
        List<Account> accounts = new ArrayList<>();

        // create sample accounts
        Account account1 = new Account("John","Silver");
        Account account2 = new Account("Madhu","Platinum");
        Account account3 = new Account("Luca","Gold");

        // add these accounts to our list
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        return accounts;

    }

    @Override
    public void addAccount(Account account,boolean vipFlag) {
        System.out.println(getClass() + " Doing my db work: Adding my account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");

        return false;
    }

    public String getName() {
        System.out.println(getClass() + " in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
