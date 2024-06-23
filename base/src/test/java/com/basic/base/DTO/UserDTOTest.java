package com.basic.base.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDTOTest {
    @InjectMocks
    private UserDTO userDTO;

    private final String userName = "testuser";
    private final String email = "testuser@example.com";
    private final String password = "testpassword";
    private final Gender gender = Gender.MALE;
    private final String phoneNumber = "1234567890";
    private final AccountType accountType = AccountType.SAVINGS;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userDTO.setUserName(userName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setGender(gender);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setAccountType(accountType);
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(userDTO.getUserName()).isEqualTo(userName);
        assertThat(userDTO.getEmail()).isEqualTo(email);
        assertThat(userDTO.getPassword()).isEqualTo(password);
        assertThat(userDTO.getGender()).isEqualTo(gender);
        assertThat(userDTO.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(userDTO.getAccountType()).isEqualTo(accountType);
    }

    @Test
    public void testNoArgsConstructor() {
        UserDTO newUserDTO = new UserDTO();
        assertThat(newUserDTO).isNotNull();
    }

    @Test
    public void testToString() {
        String toString = userDTO.toString();
        assertThat(toString).contains(userName, email, password, gender.toString(), phoneNumber, accountType.toString());
    }

}
