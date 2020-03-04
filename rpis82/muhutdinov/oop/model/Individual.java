package rpis82.muhutdinov.oop.model;

public class Individual {
    public Account[] accounts;
    public int size;
//Конструкторы
    public Individual(){
        accounts = new Account[16];
    }
    public Individual(int size){
        accounts = new Account[size];
    }
    public Individual(Account[] accounts){
        this.accounts = accounts;
    }
//Добавить ссылку
    public boolean add (Account account) {
        accounts[size++] = account;
        return true;
    }
    public boolean add (int index, Account account){
        accounts[index] = account;
        return true;
    }
//Получить ссылку
    public Account get(int size) {
        return accounts[size];
    }
    //public Account get(String accountNumber){
    //    System.out.println(accounts.accountNumber);
    //    return accounts
   // }
}
