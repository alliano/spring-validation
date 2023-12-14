package com.spring.validation.springvalidation.helper;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.spring.validation.springvalidation.Person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * saat kita meng implementasikan interface
 * dan interface tersebut memiliki annotasi
 * @Validated maka sebenarnya kita bisa menghaous
 * annotasi @validated pada class ini
 */
@Component @Validated 
public class Example implements Greeting {
    

    /**
     * kita bisa melakukan validasi di level method seperti ini
     * @param name
     * @return
     */

    public String sayHello(@NotBlank String name) {
        return "Hallo ".concat(name);
    }

    // untuk melakukan validasi parameter object
    public Person createPerson(@Valid Person person) {
        return person;
    }

    @Override
    public String greeting(@NotBlank String name) {
        return "Assalamuallaikum wahai ".concat(name);
    }
}
