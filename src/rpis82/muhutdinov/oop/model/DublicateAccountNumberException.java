package rpis82.muhutdinov.oop.model;

import java.util.Objects;

public class DublicateAccountNumberException extends Exception {
    public DublicateAccountNumberException() {
        super();
    }

    public DublicateAccountNumberException(String errorWarning) {
        super(errorWarning);
    }

    public DublicateAccountNumberException(String errorWarning, Objects objects){
        super(errorWarning + objects.toString());

    }
}
