package com.nnk.springboot.CrudUnitTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTests {
    @Autowired
    private UserRepository userRepository;

    //Crud Operations tests
    @Test
    @DisplayName("Given a user -> It is correctly done a CRUD")
    public void GivenAUser_ThenCorrectlySavedinDB() {
        //create
        User user = new User("pmirand","test","PM","role");
        userRepository.save(user);

        //read
        assertTrue(userRepository.findById(user.getId()).get().getId() > 0);

        //update
        user.setFullname("PMM");
        userRepository.save(user);
        assertEquals(userRepository.findById(user.getId()).get().getFullname(),"PMM");

        //delete
        int userId = user.getId();
        userRepository.deleteById(userId);
        assertFalse(userRepository.findById(userId).isPresent());
    }

}
