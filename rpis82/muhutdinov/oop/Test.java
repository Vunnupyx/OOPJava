package rpis82.muhutdinov.oop;
import rpis82.muhutdinov.oop.model.Account;
import rpis82.muhutdinov.oop.model.Individual;

class Test {

public static void main (String[] args) {
 //System.out.println("Я сделяль!");
 lab1tests();
}
static void lab1tests(){
 Account first = new Account("12345", 2000);
 Account second = new Account();
 System.out.println("Номер: " + first.getNumber() + " Баланс: " + first.getBalance());
 System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
 second.setNumber("4321");
 second.setBalance(1200);
 System.out.println("Номер: " + second.getNumber() + " Фамилия: " + second.getBalance());
 System.out.println(first.number);
}
}