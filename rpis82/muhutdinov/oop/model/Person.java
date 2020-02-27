package rpis82.muhutdinov.oop.model;

public class Person {
    public static final String EMPTY_FIRST_NAME = "";
    public static final String EMPTY_SECOND_NAME = "";

    public String firstName;
    public String secondName;
    public Person(String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
