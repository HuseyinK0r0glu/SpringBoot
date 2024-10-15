package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDao;
import com.luv2code.aopdemo.dao.MembershipDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao){

		return runner -> {

			demoTheBeforeAdvice(accountDao,membershipDao);
		};

	}

	public void demoTheBeforeAdvice(AccountDao accountDao,MembershipDao membershipDao){
		// call the business code
		Account account = new Account();
		accountDao.addAccount(account,true);
		accountDao.doWork();

		// call the membership business method
		membershipDao.addSillyMember();
		membershipDao.goToSleep();
	}

}
