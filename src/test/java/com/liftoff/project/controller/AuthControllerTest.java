package com.liftoff.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liftoff.project.controller.request.LoginRequestDTO;
import com.liftoff.project.controller.request.SignupRequestDTO;
import com.liftoff.project.controller.response.JwtResponseDTO;
import com.liftoff.project.controller.response.UserResponseDTO;
import com.liftoff.project.exception.UserExistsException;
import com.liftoff.project.mapper.UserMapper;
import com.liftoff.project.model.User;
import com.liftoff.project.repository.UserRepository;
import com.liftoff.project.service.UserService;
import com.liftoff.project.service.UserValidationService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {




    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private UserValidationService userValidationService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    UserRepository userRepository;

    @Mock
    private UserMapper userMapper;


    @Test
    void registerUser() {

        // given
        SignupRequestDTO signUpRequestDTO = SignupRequestDTO.builder()
                .withFirstName("John133")
                .withLastName("Doe13553")
                .withUsername("johnDoe553@gmail.com")
                .withPassword("TEST1234")
                .build();

        UserResponseDTO responseDTO = UserResponseDTO.builder()
                .withFirstName("John133")
                .withLastName("Doe13553")
                .withUsername("johnDoe553@gmail.com")
                .withIsEnabled(false)
                .withUuid(UUID.fromString("27e21ae6-515b-4871-b7f2-ee19125c54f7"))
                .build();


        when(userService.addUser(any())).thenReturn(responseDTO);
        //when


        // then
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(signUpRequestDTO)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("John133")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Doe13553")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("johnDoe553@gmail.com")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void authenticateUser() {


        // given

        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .withUsername("test_email@example.com")
                .withPassword("test1234")
                .build();


        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO("TOKEN_STRING",
                "test_email@example.com",
                "ROLE_USER", "Steve", "Gadd");

        when(userService.authenticateUser(any())).thenReturn(jwtResponseDTO);
        //when


        // then
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(loginRequestDTO)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("test_email@example.com")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Test
    void shouldThrowUserExistsExceptionWhenUserWithUsernameAlreadyExists() {
        // given
        SignupRequestDTO signUpRequestDTO = SignupRequestDTO.builder()
                .withFirstName("John133")
                .withLastName("Doe13553")
                .withUsername("johndoe553@gmail.com")
                .withPassword("TEST1234")
                .build();

        User existingUser = new User();
        existingUser.setFirstName("John");
        existingUser.setLastName("Doe");
        existingUser.setUsername("johndoe553@gmail.com");

        when(userRepository
                .findByUsername(signUpRequestDTO.getUsername()))
                .thenReturn(Optional.of(existingUser));

        // when/then
        assertThrows(UserExistsException.class, () -> {
            userValidationService.validateUsername(signUpRequestDTO);
        });
    }


    
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}