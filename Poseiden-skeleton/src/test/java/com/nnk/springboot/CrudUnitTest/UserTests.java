package com.nnk.springboot.CrudUnitTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.ClientUserDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientUserDetailsService clientUserDetailsService = new ClientUserDetailsService();

    //Crud Operations tests
    @Test
    @Transactional
    @DisplayName("Given a user -> It is correctly done a CRUD")
    public void givenAUserThenCorrectlySavedinDB() {

        //create
        User user = new User("testerUser","$2a$12$GMfjcJHr85d37Jzl0rMOXe1Us7PuJEWb.Kf3dBZQ7v0Taxr5KwHbS","PM","ADMIN");
        user = userRepository.save(user);

        //read
        Assert.assertNotNull(user.getId());

        //update
        user.setFullName("PMM");
        user = userRepository.save(user);
        assertEquals(userRepository.findById(user.getId()).get().getFullName(),"PMM");

        //delete
        int userId = user.getId();
        userRepository.deleteById(userId);
        assertFalse(userRepository.findById(userId).isPresent());
    }

    @Test
    @DisplayName("Avoid duplicated usernames once registered")
    public void givenInputUserNameThenCheckIfNotDuplicatedThenSaveIntoDB() {
        //create
        assertFalse(clientUserDetailsService.userExists("mickeymouse")); // not exists, can be added
        assertTrue(clientUserDetailsService.userExists("admin")); //already existgs

    }



}
