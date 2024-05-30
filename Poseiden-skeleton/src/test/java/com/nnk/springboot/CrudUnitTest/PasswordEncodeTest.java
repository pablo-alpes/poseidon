package com.nnk.springboot.CrudUnitTest;

import com.nnk.springboot.service.PasswordCheckServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Pablo Miranda
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordEncodeTest {

    @ParameterizedTest
    @CsvSource({"123456@A", "A1234567@","aaaaaaa1A@"})
    @DisplayName("Valid password allowed")
    public void validatePassword(String input) {
        //Arrange
        String pw = input;
        PasswordCheckServiceImpl pwdCheck = new PasswordCheckServiceImpl();

        //ACT & Assert
        assertTrue(pwdCheck.passwordCheck(pw));

    }

    @ParameterizedTest
    @CsvSource({"1234567a", "12345@a", "aaaaaaaA", "aaaaaaa1A"})
    @DisplayName("Invalid Passwords not allowed")
    public void InvalidPasswordsNotAllowed(String input) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //Arrange
        String pw = input;
        PasswordCheckServiceImpl pwdCheck = new PasswordCheckServiceImpl();

        //ACT & Assert
        assertFalse(pwdCheck.passwordCheck(pw));

    }
}
