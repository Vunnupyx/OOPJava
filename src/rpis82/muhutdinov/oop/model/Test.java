package rpis82.muhutdinov.oop.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Test {

    public static void main(String[] args) {
        //System.out.println("Я сделяль!");
        lab7tests();
    }

    /* static void lab4tests(){
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

     }*/
    /*static void lab5tests() {
        LocalDate localDate = LocalDate.of(2020, 3, 15);
        LocalDate localDate2 = LocalDate.of(2020, 6, 15);

        LocalDate localDate1 = LocalDate.of(2021, 12, 23);
        AbstractAccount abstractAccount = new AbstractAccount("40321810300020000041", -666, localDate, localDate2);
        AbstractAccount abstractAccount1 = new AbstractAccount("40321810300020000041", localDate1);
//AbstractAccount abstractAccount1 = new AbstractAccount(null, null);
        //   System.out.println(abstractAccount1.toString());
        System.out.println(localDate.isAfter(LocalDate.now()));
        System.out.println(abstractAccount.monthesQuantityBeforeExpiration());
        System.out.println("<-------CreditAccount------>");
        CreditAccount creditAccount = new CreditAccount(abstractAccount.getNumber(), abstractAccount.getBalance(), 25, localDate, localDate2);
        CreditAccount creditAccount2 = new CreditAccount(abstractAccount1.getNumber(), localDate1);

        System.out.println("EQUALS " + creditAccount.equals(creditAccount2));
        try {
            CreditAccount cloneCredit = creditAccount.clone();
            System.out.println(cloneCredit.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(creditAccount.nextPaymentDate());
        System.out.println(creditAccount.nextPaymentValue());
        System.out.println("<-------DebitAccount------>");
        DebitAccount adss = new DebitAccount("40321810300020000001", 2000, localDate, localDate2);

        try {
            DebitAccount cloneAccount2 = adss.clone();
            System.out.println(cloneAccount2.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
String number ="40321810300020000001";
        Pattern pattern = Pattern.compile("(44|45|40)\\d{3}(810)\\d{4}[1-9]\\d{6}[1-9]");
        Pattern patterns = Pattern.compile("[44-45]|40\\d{3}[810]\\d\\d{3}[1-9]");
        Matcher matcherNumber = pattern.matcher(number);
        if ( matcherNumber.matches() )
        {
            System.out.println("true");
        }
        else System.out.println("false");

        System.out.println("<-------Individual------>");
        Account[] accounts = {abstractAccount, adss};
        Individual individual = new Individual("MyName", accounts);

        individual.addCreditScores(2);
        System.out.println(individual.toString());
        //System.out.println(individual.hasAccount("4032181030002000001"));
        //Entity ae = new Entity("ada", accounts);
    }*/
    /*static void lab6tests() {

        LocalDate localDate = LocalDate.of(2020, 3, 15);
        LocalDate localDate2 = LocalDate.of(2020, 6, 15);

        LocalDate localDate1 = LocalDate.of(2021, 12, 23);
        AbstractAccount abstractAccount = new AbstractAccount("40321810300020000041", -666, localDate, localDate2);
        AbstractAccount abstractAccount1 = new AbstractAccount("40321810300020000051", localDate1);

        System.out.println("<-------CreditAccount------>");
        CreditAccount creditAccount = new CreditAccount(abstractAccount.getNumber(), abstractAccount.getBalance(), 25, localDate, localDate2);
        CreditAccount creditAccount2 = new CreditAccount(abstractAccount1.getNumber(),-2500, 25, localDate, localDate1);

        System.out.println("<-------DebitAccount------>");
        DebitAccount adss = new DebitAccount("40321810300020000001", 2000, localDate, localDate2);

        System.out.println("<-----------------Individual--------------------->");
        Account[] accounts = {adss, abstractAccount, creditAccount};
        Individual individual = new Individual("MyName", accounts);
        Account[] accountss = {adss, abstractAccount, creditAccount2};
        try {
            Client entity = new Entity("SecondName", accountss);
            System.out.println(entity.debtTotal());
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
        System.out.println(individual.debtTotal());
        System.out.println("<-----------------Iterator--------------------->");
        Iterator<Account> accountIterator = individual.iterator();
        System.out.println(accountIterator.next());
        System.out.println(accountIterator.next());

        Account[] accountsNotSort = individual.getAccounts();
        for (Account account : accountsNotSort)
            System.out.println(account.getBalance());
        Account[] sortAccount = individual.sortedAccountByBalance();
        for (Account account : sortAccount)
            System.out.println(account.getBalance());


    }*/
    static void lab7tests() {
        LocalDate localDate = LocalDate.of(2020, 3, 15);
        LocalDate localDate1 = LocalDate.of(2020, 6, 15);
        LocalDate localDate2 = LocalDate.of(2021, 12, 23);

        DebitAccount debitAccount = new DebitAccount("40321810300020000001", 2000, localDate, localDate1);
        DebitAccount debitAccount1 = new DebitAccount("40321810300020000002", 2400, localDate, localDate2);
        CreditAccount creditAccount = new CreditAccount(debitAccount.getNumber(), -40000, 25, localDate, localDate1);

        Account[] accounts = {debitAccount, creditAccount};
        System.out.println("<-----------------Entity--------------------->");
        Entity entity = new Entity("MyName", accounts);
        System.out.println("isEmpty: " + entity.isEmpty());
        System.out.println("contains: " + entity.contains(creditAccount));
        Account[] accounts1 = entity.toArray();
        for (Account account : accounts1)
            System.out.println(account.getBalance());
        entity.add(debitAccount1);
        System.out.println(entity.toString());
        entity.remove(debitAccount);
        System.out.println(entity.toString());
        System.out.println(entity.containsAll(Arrays.asList(accounts)));
        /*entity.clear();
        System.out.println(entity.debtTotal());*/
        System.out.println("<-----------------Individual--------------------->");
        Individual individual = new Individual("Name", accounts);
        System.out.println("isEmpty: " + individual.isEmpty());
        System.out.println("contains: " + individual.contains(creditAccount));
        Account[] accounts12 = individual.toArray();
        for (Account account : accounts12)
            System.out.println(account.getBalance());
        individual.add(debitAccount1);
        System.out.println(individual.toString());
       // individual.remove(creditAccount);
        System.out.println(individual.toString());
        System.out.println(individual.containsAll(Arrays.asList(accounts)));
        System.out.println(individual.contains(debitAccount));
        List<Account> list = individual.sortedAccountByBalance();
        for (Account account : list)
            System.out.println(account.getBalance());
        System.out.println("<-----------------AccountManager--------------------->");
        Client[] clients = {individual, entity};
        AccountManager accountManager = new AccountManager(clients);
        List<Client> list1 = accountManager.sortedByBalanceClients();
        Client[] clients1 = accountManager.getClients();
        for (Client client : list1)
            System.out.println(client.totalBalance());
    }
}