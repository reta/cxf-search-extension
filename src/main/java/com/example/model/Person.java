package com.example.model;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
        
    public Person() {
    }
    
    public Person( final String firstName, final String lastName, final int age ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setAge(age);
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }
    
    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }        
}
