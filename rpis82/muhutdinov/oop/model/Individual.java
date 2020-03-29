package rpis82.muhutdinov.oop.model;

public class Individual implements Client{
    public Account[] accounts;
    public int size;
    public String name;

    //Конструкторы
    public Individual(String name) {
        accounts = new Account[16];
        size = 16;
        this.name = name;
    }

    public Individual(String name, int size) {
        accounts = new Account[size];
        this.size = size;
        this.name = name;
    }

    public Individual(String name, Account[] accounts) {
        this.accounts = accounts;
        size = accounts.length;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    // Доп. метод Расширить
    public void extendAccounts() {
        Account[] newAccount = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccount, 0, accounts.length);
        accounts = newAccount;
    }

    //Добавить ссылку
    public boolean add(Account account) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                size++;
                return true;
            }
        }
        extendAccounts();
        accounts[accounts.length / 2] = account;
        size++;
        return true;
    }

    public boolean add(int index, Account account) {
        while (accounts.length - 1 < index) {
            extendAccounts();
        }
        if (accounts[index] == null) {
            size++;
        }
        accounts[index] = account;
        return true;
    }

    //Получить ссылку
    public Account get(int index) {
        return accounts[index];
    }

    public Account get(String accountNumber) {
        for (Account account : accounts) {
            if (account != null && account.number.equals(accountNumber))
                return account;
        }
        return null;
    }

    //Проверить есть ли ссылка с заданным номером
    public boolean hasAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account != null && account.number.equals(accountNumber))
                return true;
        }
        return false;
    }

    //Изменить ссылку по номеру массива
    public Account set(int index, Account account) {
        Account lastAccount = accounts[index];
        accounts[index] = account;
        return lastAccount;
    }

    //Удалить ссылку
    public Account remove(int index) {

        if (accounts.length - 1 >= index) {
            Account lastAccount = accounts[index];
            System.arraycopy(accounts, index + 1, accounts, index, accounts.length - 1 - index);
            accounts[accounts.length - 1] = null;
            size--;
            return lastAccount;
        }
        return null;
    }

    public Account remove(String accountNumber) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].number.equals(accountNumber)) {
                Account lastAccount = accounts[i];
                System.arraycopy(accounts, i + 1, accounts, i, accounts.length - 1 - i);
                accounts[accounts.length - 1] = null;
                size--;
                return lastAccount;
            }
        }
        return null;
    }

    // возвращающий число физ. лиц
    public int size() {
        return size;
    }

    //возвращающий массив счетов (значений null в массиве быть не должно, его размер должен
    //быть равен числу элементов в исходном массиве)
    public Account[] getAccounts() {
        Account[] returnAccount = new Account[size];
        int count = 0;
        for (Account account : accounts) {
            if (account != null) {
                returnAccount[count] = account;
                count++;
            }
        }
        return returnAccount;
    }

    public Account[] sortedAccountByBalance() {
        Account[] returnAccount = getAccounts();
        Account copy;
        for (int i = 0; i < returnAccount.length; i++) {
            for (int j = 0; j < returnAccount.length - 1; j++) {
                if (returnAccount[j].balance > returnAccount[j + 1].balance) {
                    copy = returnAccount[j + 1];
                    returnAccount[j + 1] = returnAccount[j];
                    returnAccount[j] = copy;
                }
            }
        }
        return returnAccount;
    }

    public double totalBalance() {
        double sumBalance = 0;
        for (Account account : accounts) {
            if(account != null) {
                sumBalance += account.balance;
            }
        }
        return sumBalance;
    }
}