package rpis82.muhutdinov.oop.model;

import rpis82.muhutdinov.oop.model.Account;
import rpis82.muhutdinov.oop.model.AccountManager;
import rpis82.muhutdinov.oop.model.Individual;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Objects;

class Test {

    public static void main(String[] args) {
        //System.out.println("Я сделяль!");
        lab4tests();
    }

    static void lab4tests(){
        AbstractAccount ada = new AbstractAccount("5678", 2000);

        System.out.println(ada.toString());
        System.out.println(ada.hashCode());
        System.out.println(ada.toString());
        System.out.println(ada.hashCode());
        try {
            Account cloneAccount = ada.clone();
            System.out.println(cloneAccount.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("<-------DebitAccount------>");
        DebitAccount adss = new DebitAccount("5555", 2000);

        try {
            DebitAccount cloneAccount2 = adss.clone();
            System.out.println(cloneAccount2.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("<-------CreditAccount------>");
        Account creditAccount = new CreditAccount(ada.getNumber(), ada.getBalance(), 25);
        CreditAccount creditAccount2 = new CreditAccount(adss.getNumber(), adss.getBalance(), 35);
        System.out.println("EQUALS " + creditAccount.equals(creditAccount2));
        try {
            CreditAccount cloneCredit = creditAccount2.clone();
            System.out.println(cloneCredit.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("<-------Individual------>");
        Account[] accounts = {ada, adss};
        Individual individual = new Individual("MyName", accounts);

        individual.addCreditScores(2);
        System.out.println(individual.toString());
        System.out.println(individual.hashCode());


        System.out.println("<-------AccountManger------>");

        Account account = new DebitAccount("5678", 2000);
        Account account1 = new DebitAccount("8900", 3000);
        Account[] accounts1 = {account,account1};
        Client individual1 = new Individual("sada", accounts1);
        Client[] clients = {individual, individual1};

        AccountManager  accountManager = new AccountManager(clients);

        System.out.println(accountManager.toString());

        System.out.println("<-------Individual and Entity------>");

        System.out.println(individual.debtTotal());
        Account[] accounts2 = {creditAccount, creditAccount2};
        Client client = new Individual("кредиты", accounts2);
        System.out.println(client.debtTotal());
        System.out.println("<-------Finish------>");

    }
}