package rpis82.muhutdinov.oop.model;

import rpis82.muhutdinov.oop.model.Account;
import rpis82.muhutdinov.oop.model.AccountManager;
import rpis82.muhutdinov.oop.model.Individual;

import java.sql.SQLOutput;
import java.util.Arrays;

class Test {

    public static void main(String[] args) {
        //System.out.println("Я сделяль!");
        lab3tests();
    }

    static void lab3tests(){
        Account debitAccount = new DebitAccount();
        Account debitAccountSecond = new DebitAccount("1234", 1000);

        Account account = new DebitAccount("5678", 2000);
        Account account1 = new DebitAccount("8900", 3000);

        Account creditAccount = new CreditAccount(account.getNumber(), account.getBalance(), 25);
        Account creditAccount2 = new CreditAccount(debitAccountSecond.getNumber(), debitAccountSecond.getBalance(), 35);



        Account[] accounts = {debitAccount, debitAccountSecond};
        Account[] accounts1 = {account,account1};
        Account[] accounts2s = {creditAccount, creditAccount2, account};


        Client individual = new Individual("Yes", accounts);
        Client individual1 = new Individual("Not", accounts1);
        Client clientr = new Individual("YesYes", accounts2s);

        clientr.addCreditScores(-3);
        individual.addCreditScores(-3);
        System.out.println(individual.getStatus().name());

        Account[] accounts2 = individual1.getCreditAccounts();
        System.out.println(accounts2.length);
        for (Account account2 : accounts2){
            System.out.println(account2.getNumber());
        }

        Client[] clients = {individual, individual1, clientr};

        AccountManager  accountManager = new AccountManager(clients);
        Client[] clients1 = accountManager.getWickedDebtors();
        for (Client client : clients1){
            System.out.println(client.getName());
        }
    }
}