package com.spring.validation.springvalidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.validation.springvalidation.helper.Example;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class ValidateMethodTest {
    
    @Autowired
    private Example example;

    @Test
    public void testValidateMethodFaill() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            String sayHello = this.example.sayHello("");
            Assertions.assertNull(sayHello);
        });
    }

    @Test
    public void testValidateMethodSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            String sayHello = this.example.sayHello("Alliano");
            Assertions.assertEquals("Hallo Alliano", sayHello);
        });
    }

    @Test
    public void testValidatePersonAsParameterFaill() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            Person person = this.example.createPerson(new Person());
            Assertions.assertNotNull(person);
        });
    }

    @Test
    public void testValidatePersonAsParameterSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            Person personReq = Person.builder()
                            .firstName("Abdillah")
                            .lastName("Alli")
                            .build();
            Person person = this.example.createPerson(personReq);
            Assertions.assertEquals("Abdillah", person.getFirstName());
            Assertions.assertEquals("Alli", person.getLastName());
        });
    }

    @Test
    public void testValidationWithInterfaceFaill() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            String greeting = this.example.greeting("");
            Assertions.assertNull(greeting);
        });
    }
    @Test
    public void testValidationWithInterfaceSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            String greeting = this.example.greeting("Alliano");
            Assertions.assertNotNull(greeting);
            Assertions.assertEquals("Assalamuallaikum wahai Alliano", greeting);
        });
    }
}
