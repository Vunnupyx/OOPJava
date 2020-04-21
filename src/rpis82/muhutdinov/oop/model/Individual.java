package rpis82.muhutdinov.oop.model;

public class Individual {
    public Account[] accounts;
    public int size;
    private final int DEFAULT_ELEMENTS = 16;

    //Конструкторы
    public Individual() {
        accounts = new Account[DEFAULT_ELEMENTS];
        size = DEFAULT_ELEMENTS;
    }

    public Individual(int size) {
        accounts = new Account[size];
        this.size = size;
    }

    public Individual(Account[] accounts) {
        this.accounts = accounts;
        size = accounts.length;
    }

    // Доп. метод Расширить
    private void extendAccounts() {
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
        //while расширяет массив до тех пор пока индекс не входит в массив

//        while (accounts.length - 1 < index) {
//            extendAccounts();
//        }
//        if (accounts[index] == null) {
//            size++;
//        }
        accounts[index] = account;
        return true;
    }

    //Получить ссылку
    public Account get(int index) {
        return accounts[index];
    }

    public Account get(String accountNumber) {
        for (Account account : accounts) {
            if (compareAccountNumber(account, accountNumber))
                return account;
        }
        return null;
    }

    private boolean compareAccountNumber(Account account, String accountNumber) {
        return account != null && account.number.equals(accountNumber);
    }

    //Проверить есть ли ссылка с заданным номером
    public boolean hasAccount(String accountNumber) {
        for (Account account : accounts) {
            if (compareAccountNumber(account, accountNumber))
                return true;
        }
        return false;
    }

    //Изменить ссылку по номеру массива
    public Account set(int index, Account account) {
        Account lostAccount = accounts[index];
        accounts[index] = account;
        return lostAccount;
    }

    //Удалить ссылку
    public Account remove(int index) {
        if (accounts.length - 1 >= index) {
            Account lostAccount = accounts[index];
            System.arraycopy(accounts, index + 1, accounts, index, accounts.length - 1 - index);
            accounts[accounts.length - 1] = null;
            size--;
            return lostAccount;
        }
        return null;
    }

    public Account remove(String accountNumber) {
        for (int i = 0; i < accounts.length; i++) {
            if (compareAccountNumber(accounts[i], accountNumber)) {
                Account lostAccount = remove(i);
                return lostAccount;
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
        System.arraycopy(accounts, 0, returnAccount, 0, size);
        //int count = 0;
//        for (Account account : accounts) {
//            if (account != null) {
//                returnAccount[count] = account;
//                count++;
//            }
//        }
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
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                sumBalance += accounts[i].balance;
            }
        }
        return sumBalance;
    }
}