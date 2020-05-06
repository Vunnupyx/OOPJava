package rpis82.muhutdinov.oop;

import rpis82.muhutdinov.oop.model.*;

class Test {

    public static void main(String[] args) {
        //System.out.println("Я сделяль!");
        lab3tests();
    }

//    static void lab1tests() {
//
//        //Account
//
////        Account first = new Account("12345",2000);
////        Account second = new Account();
////        System.out.println("Номер: " + first.getNumber() + " Баланс: " + first.getBalance());
////        System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
////        second.setNumber("4321");
////        second.setBalance(1200);
////        System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
////        System.out.println(first.number);
//
    //Individual

//        Account a = new Account("1", 20000);
//        Account b = new Account("2", 20001);
//        Account c = new Account("3", 20002);
//        Account d = new Account("4", 20003);
//        Account e = new Account("5", 20005);
//        Account f = new Account("6", 20040);
//        Account g = new Account("7", 20200);
//        Account h = new Account("8", 20300);

    //
//        Account[] arrayAccounts = {h, c, d};
//
//        //Физическое лицо, имеющее несколько счетов.
//        Individual ind = new Individual(arrayAccounts);
//
//        //Добавить ссылку
//        ind.add(a);
//        ind.add(16, g);
//        ind.add(e);
//        ind.add(f);
//        ind.add(b);
//
//        //Получить ссылку
//        System.out.println(ind.get(16).number);
//        System.out.println(ind.get("3").number);
//
//        //Проверить есть ли ссылка с заданным номером
//        System.out.println(ind.hasAccount("3"));
//
//        //Изменить ссылку по номеру массива
//        ind.set(16, e);
//        System.out.println(ind.get(16).number);
//
//        //Удалить ссылку
//        System.out.println(ind.size());
//        ind.remove(1);
//        System.out.println(ind.size());
//        ind.remove("1");
//        System.out.println(ind.size());
//
//        //Получить массив ссылок
//        Account[] accounts = ind.getAccounts();
//        for (Account account : accounts) {
//            System.out.println(account.balance);
//        }
//
//        System.out.println();
//
//        //Получить отсортированный массив ссылок
//        Account[] sort = ind.sortedAccountByBalance();
//        for (Account account : sort) {
//            System.out.println(account.balance);
//        }
//
//        //Получить сумму баланса всех ссылок
//        System.out.println(ind.totalBalance());
//
//        //AccountManager
//
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
//
//    }
//    static void lab2tests() {
//        Account first = new DebitAccount("12345", 2000);
//        Account dfirst = new DebitAccount("123457", 2000);
//        Account second = new DebitAccount();
//        Account Asecond = new DebitAccount("Получилось", 2000);
//        System.out.println("Номер: " + first.getNumber() + " Баланс: " + first.getBalance());
//        System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
//        second.setNumber("4321");
//        second.setBalance(1200);
//        System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
//
//        Account[] arrayAccounts = {dfirst, first, second};
//        Account[] arrayAccountss = {dfirst, second};
//
//        Entity entity = new Entity("group", arrayAccounts);
//        entity.addByIndex(1, Asecond);
//        entity.removeNodeByIndex(1);
//        System.out.println(entity.getName());
//        entity.editNode(1, Asecond);
//        entity.printList();
//        Account[] accounts = entity.sortedAccountByBalance();
//        for (Account account : accounts) {
//            System.out.println(account.getNumber());
//        }
//        System.out.println(entity.totalBalance());
//        System.out.println("Раздел");
//
//        Entity entity1 = new Entity("group1");
//        entity1.addBack(second);
//        entity1.addBack(first);
//        entity1.printList();
//
//        System.out.println(entity1.size() + "ФИНИШ");
//    }
    static void lab3tests(){
        Account debitAccount = new DebitAccount();
        Account debitAccountSecond = new DebitAccount("1234", 1000);

        Account account = new DebitAccount("5678", 2000);
        Account account1 = new DebitAccount("8900", 3000);

        Account creditAccount = new CreditAccount(debitAccount.getNumber(), debitAccount.getBalance(), 25);
        Account creditAccount2 = new CreditAccount(debitAccountSecond.getNumber(), debitAccountSecond.getBalance(), 35);

        System.out.println(account.getClass().equals(CreditAccount.class);


        Account[] accounts = {debitAccount, debitAccountSecond};
        Account[] accounts1 = {account,account1};
        Account[] accounts2s = {creditAccount, creditAccount2};


        Client individual = new Individual("Yes", accounts);
        Client individual1 = new Individual("Not", accounts1);
        Client clientr = new Individual("YesYes", accounts2s);

        Client[] clients = {individual, individual1, clientr};

                                AccountManager  accountManager = new AccountManager(clients);

//        Client[] clientsCredit = accountManager.getClients();
//        for (Client client : clientsCredit) {
//            System.out.println(client.getName());
//            Account[] accounts2 = client.getAccounts();
//            for (Account account2 : accounts2){
//                System.out.println(account2.getNumber());
//            }
//        }
        //System.out.println(creditAccount.getAPR());
    }
}