/**package com.teamrocket.projetdevop.ivvqproject.modeltests;

import com.teamrocket.projetdevop.ivvqproject.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class UserTest {

    private static Validator validator;

    @BeforeAll
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator =  factory.getValidator();
    }

    @Test
    public void userIsASellerTest() {
        User user = new User("toto1","secret1","toto","21342","France","Seller");
        Assertions.assertTrue(validator.validate(user).isEmpty());
    }


    @Test
    public void userIsACustomerTest() {
        User user = new User("toto2","secret2","totoBis","2342","France","Customer");
        Assertions.assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    public void userEmailIsNullTest()
    {
        User user = new User(null,"secret2","totoBis","2342","France","Customer");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }


    @Test
    public void userEmailIsEmptyTest()
    {
        User user = new User("","secret2","totoBis","2342","France","Customer");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userEmailWithoutArobaseTest()
    {
        User user = new User("jdb","secret2","totoBis","2342","France","Customer");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }


    @Test
    public void userNameIsEmptyTest()
    {
        User user = new User("toto","secret2","","2342","France","Customer");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userNameIsNullTest()
    {
        User user = new User("toto","secret2",null,"2342","France","Customer");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userPasswordIsNullTest()
    {
        User user = new User("jdb@",null,"jdb","2342","France","Seller");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userPasswordIsEmptyTest()
    {
        User user = new User("jdb@","","jdb","2342","France","Seller");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userAddressIsNullTest()
    {
        User user = new User("jdb@","devrs","jdbd","2342",null,"Seller");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userAddressIsEmptyTest()
    {
        User user = new User("jdb@","devrs","jdbd","2342","","Seller");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userPhoneIsNullTest()
    {
        User user = new User("jdb@","devrs","jdbd",null,"","Seller");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void userPhoneIsEmptyTest()
    {
        User user = new User("jdb@","devrs","jdbd",null,"","Seller");
        Assertions.assertFalse(validator.validate(user).isEmpty());
    }

}
**/