package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDao;
import com.luv2code.aopdemo.dao.MembershipDao;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao ,
											   TrafficFortuneService trafficFortuneService){

		return runner -> {

			//demoTheBeforeAdvice(accountDao,membershipDao);
			//demoTheAfterReturningAdvice(accountDao);
			//demoTheAfterThrowingAdvice(accountDao);
			//demoTheAfterAdvice(accountDao);
			//demoTheAroundAdvice(trafficFortuneService);
			//demoTheAroundAdviceHandleException(trafficFortuneService);

			demoTheAroundAdviceRethrowException(trafficFortuneService);
		};

	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("calling getFortune");

		String data = null;
		try {
			boolean tripWire = true;
			data = trafficFortuneService.getFortune(tripWire);
		}catch (Exception exception){
			System.out.println(exception.getMessage());
		}
		if(data != null){
			System.out.println("\nMy Fortune is: " + data);
		}

		System.out.println("finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("calling getFortune");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy Fortune is: " + data);

		System.out.println("finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("calling getFortune");

		String data = trafficFortuneService.getFortune();

		System.out.println("\nMy Fortune is: " + data);

		System.out.println("finished");
	}

	private void demoTheAfterAdvice(AccountDao accountDao) {

		// call method to find accounts
		List<Account> accounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
			accounts = accountDao.findAccounts(tripWire);
		}catch (Exception e){
			System.out.println("\n\nMain Program: caught exception :" + e);
		}
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		if(accounts != null){
			System.out.println(accounts);
		}
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDao accountDao) {

		// call method to find accounts
		List<Account> accounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accounts = accountDao.findAccounts(tripWire);
		}catch (Exception e){
			System.out.println("\n\nMain Program: caught exception :" + e);
		}
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		if(accounts != null){
			System.out.println(accounts);
		}
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDao accountDao) {
		// call method to find accounts
		List<Account> accounts = accountDao.findAccounts();
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}

	public void demoTheBeforeAdvice(AccountDao accountDao,MembershipDao membershipDao){
		// call the business code
		Account account = new Account();
		account.setName("Madhu");
		account.setLevel("plat");
		accountDao.addAccount(account,true);
		accountDao.doWork();

		// call the account dao getter/setter methods
		accountDao.setName("foobar");
		accountDao.setServiceCode("silver");
		accountDao.getName();
		accountDao.getServiceCode();

		// call the membership business method
		membershipDao.addSillyMember();
		membershipDao.goToSleep();
	}

}
