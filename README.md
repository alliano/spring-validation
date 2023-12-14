# Rquirement
* Berakal
* Sehat
* Java dasar
* Object Oriented Programming
* Bean Validation
* Spring Framework
* SpringBoot

# Depndency yang dibutuhkan 

* https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
* https://mvnrepository.com/artifact/org.projectlombok/lombok
* https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor

# Validator
Saat kita akan melakukan validasi dengan bean validation kita harus membuat sebuah object yang heavy(sangat brat) yang namanya [Validator](https://docs.oracle.com/javaee%2F7%2Fapi%2F%2F/javax/validation/Validator.html).  
Namun pada spring, kita tidak perlu membuat nya secara manual lagi, karena sudah dibuatkan oleh spring.  

# Melakukan Validasi
```java
@Builder
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Person {
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
```

```java
@SpringBootTest
class SpringValidationApplicationTests {

	@Autowired
	private Validator validation;

	@Test
	public void testValidatePersonNotValid() {
		Set<ConstraintViolation<Person>> validate = this.validation.validate(Person.builder().firstName("").build());
		Assertions.assertFalse(validate.isEmpty());
		Assertions.assertEquals(2, validate.size());
	}

	@Test
	public void testValidatePersonSucces() {
		Set<ConstraintViolation<Person>> validate = this.validation.validate(new Person("Abdilah", "alli"));
		Assertions.assertTrue(validate.isEmpty());
		Assertions.assertEquals(0, validate.size());
	}
}
```

# Custom Validator

``` java
@Component
public class PasswordChecker {
    
    public boolean isPasswordValid(String password) {
        if (password.length() < 5) return false;
        return true;
    }
}
```

``` java
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    
    public String message() default "{password.message}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
```

``` java
@AllArgsConstructor
public class PasswordValidator implements ConstraintValidator<Password, String>{

    private final PasswordChecker passwordChecker;

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        return this.passwordChecker.isPasswordValid(arg0);
    }
}
```

Cara penggunaanya cukup sederhana.  
``` java
@Builder
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class LoginRequest {
    
    @Email @NotBlank
    private String email;

    @Password @NotBlank
    private String password;
}
```

Unit test

``` java
@Test
public void testPasswordInValid() {
    LoginRequest loginRequest = LoginRequest.builder()
                .email("alliano@gmail.com")
                .password("ab")
                .build();
    Set<ConstraintViolation<LoginRequest>> validate = this.validator.validate(loginRequest);
    Assertions.assertEquals(1, validate.size());
    Assertions.assertFalse(validate.isEmpty());
}

@Test
public void testPasswordValid() {
   LoginRequest loginRequest = LoginRequest.builder()
                .email("alliano@gmail.com")
                .password("cjery39797dd")
                .build();
    Set<ConstraintViolation<LoginRequest>> validate = this.validator.validate(loginRequest);
    Assertions.assertTrue(validate.isEmpty());
    Assertions.assertEquals(0, validate.size());
}
```
# Message Source
Jikalau kita ingin pesan validasi error disampaikan dalam beberapa bahasa maka kita bisa memanfaatkan fitur message sorce.  

Pertama kita buat dulu file dengan nama messages.properties (sesuaikan dengan locale yang igin digunakan) setelah itu isikan error message yang ingin ditampilkan 

``` properties
palindrome.message = kata, bukan palindrome
password.message = password harus lebih dari 5 karakter
```

dan untuk penggunaanya cukup mudah 

``` java
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    
    public String message() default "{password.message}"; // untuk pesan errornya diambil dari message soruce

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
```

# Method validation
Untuk melakukan validasi terhadap kita bisa menggunakan annotation @Validated pada class dan pada method nya kita bisa menggunakan validation annotation yang kita butuhkan

``` java
 */
@Component @Validated 
public class Example {
    

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
}
```

# Method Overiding
Saat kita melakukan implementasi pada interface, terkadang kita ingin melakukan validasi pada method, jika setelh implementasi kita lansung melakukan validasi paada method implementasinya maka error dari interface akan terjadi, karena kita mencoba mengubah method implementasinya.  

Maka untuk melakukan validasi pada interface, kita langsung saja melakukan validasi pada interface nya bukan dari class implementasi nya

``` java
@Validated
public interface Greeting {
    
    public String greeting(@NotBlank String name);
}
```

``` java
/**
 * saat kita meng implementasikan interface
 * dan interface tersebut memiliki annotasi
 * @Validated maka sebenarnya kita bisa meghapus
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
```

# Configuration properties
Validasi juga bisa kita gunakan pada configuration properties, caranya cukup sederhana, cukup kita berikan annotation @Validated pada class ConfigurationProperties setelah itu kita gunakan annoation Validation pada field nya 

``` java
@Validated
@Setter @Getter @ConfigurationProperties(prefix = "subcriber")
public class SubcriberProperties {
    
    @NotBlank
    private String url;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String driver;
}
```

``` java
@EnableConfigurationProperties(value = {
	SubcriberProperties.class
})
@SpringBootApplication
public class SpringValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringValidationApplication.class, args);
	}
}
```

