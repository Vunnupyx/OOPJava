package rpis82.muhutdinov.oop;
import rpis82.muhutdinov.oop.model.Person;

class Test {

public static void main (String[] args) {
 //System.out.println("Я сделяль!");
 lab1tests();
}
static void lab1tests(){
 Person firstEmployee = new Person("Вася", "Пупкин");
 Person secondEmployee = new Person(Person.EMPTY_FIRST_NAME, Person.EMPTY_SECOND_NAME);
 System.out.println("Имя: " + firstEmployee.getFirstName());
 System.out.println("Фамилия: " + firstEmployee.getSecondName());
 System.out.println("Имя: " + secondEmployee.getFirstName());
 System.out.println("Фамилия: " + secondEmployee.getSecondName());
}
}