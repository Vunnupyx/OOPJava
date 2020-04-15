package rpis82.muhutdinov.oop;

import rpis82.muhutdinov.oop.model.Account;
import rpis82.muhutdinov.oop.model.AccountManager;
import rpis82.muhutdinov.oop.model.Individual;

import java.sql.SQLOutput;
import java.util.Arrays;

class Test {

    public static void main(String[] args) {
        //System.out.println("Я сделяль!");
        lab1tests();
    }

    static void lab1tests() {

        //Account

//        Account first = new Account("12345",2000);
//        Account second = new Account();
//        System.out.println("Номер: " + first.getNumber() + " Баланс: " + first.getBalance());
//        System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
//        second.setNumber("4321");
//        second.setBalance(1200);
//        System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
//        System.out.println(first.number);

        //Individual

        Account a = new Account("1", 20000);
        Account b = new Account("2", 20001);
        Account c = new Account("3", 20002);
        Account d = new Account("4", 20003);
        Account e = new Account("5", 20005);
        Account f = new Account("6", 20040);
        Account g = new Account("7", 20200);
        Account h = new Account("8", 20300);


        Account[] arrayAccounts = {h, c, d};

        //Физическое лицо, имеющее несколько счетов.
        Individual ind = new Individual(arrayAccounts);

        //Добавить ссылку
        ind.add(a);
        ind.add(e);
        ind.add(f);
        ind.add(b);
        System.out.println("тут");
        //Получить ссылку
        System.out.println(ind.get("3").number);

        //Проверить есть ли ссылка с заданным номером
        System.out.println(ind.hasAccount("3"));

        //Изменить ссылку по номеру массива

        //Удалить ссылку
        System.out.println(ind.size());
        ind.remove(1);
        System.out.println(ind.size());
        ind.remove("1");
        System.out.println(ind.size());

        //Получить массив ссылок
        Account[] accounts = ind.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.balance);
        }

        System.out.println();

        //Получить отсортированный массив ссылок
        Account[] sort = ind.sortedAccountByBalance();
        for (Account account : sort) {
            System.out.println(account.balance);
        }

        //Получить сумму баланса всех ссылок
        System.out.println(ind.totalBalance());

        //AccountManager

//        Account a = new Account("1", 20000);
//        Account b = new Account("2", 20001);
//        Account c = new Account("3", 20002);
//        Account d = new Account("4", 20003);
//        Account e = new Account("5", 20005);
//        Account f = new Account("6", 20040);
//        Account g = new Account("7", 20200);
//        Account h = new Account("8", 20300);
//        Account i = new Account("9", 20400);
//        Account[] firstAccounts = {h, c, d};
//        Account[] secondAccounts = {g, e, f};
//        Account[] thirdAccounts = {b, i};
//        Account[] fourthAccounts = {a, i};
//
//
//        Individual firstInd = new Individual(firstAccounts);
//        Individual secondInd = new Individual(secondAccounts);
//        Individual thirdInd = new Individual(thirdAccounts);
//        Individual fourthInd = new Individual(fourthAccounts);
//        Individual[] arrayInd = {firstInd, secondInd};
//
//        AccountManager accountManager = new AccountManager(arrayInd);
//        accountManager.add(thirdInd);
//        accountManager.add(12, fourthInd);
//
//        System.out.println("account getIndividuals");
//        Individual[] storage = accountManager.getIndividuals();
//        for (Individual individual : storage) {
//            for (Account account : individual.accounts) {
//                System.out.println(account.number);
//            }
//        }
//
//        System.out.println("size " + accountManager.size());
//        System.out.println("getAccount " + accountManager.getAccount("4").number);
//        System.out.println("removeAccount " + accountManager.removeAccount("5").number);
//        System.out.println("remove test:");
//        Individual[] storages = accountManager.getIndividuals();
//        for (Individual individual : storages) {
//            for (Account account : individual.accounts) {
//                System.out.println(account.number);
//            }
//        }
//        System.out.println("sortedByBalanceIndividuals:");
//        Individual[] storagess = accountManager.sortedByBalanceIndividuals();
//        for (Individual individual : storagess) {
//            for (Account account : individual.accounts) {
//                System.out.println(account.number);
//            }
//        }

    }
}