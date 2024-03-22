package esportsclash.pratique.auth;

import esportsclash.pratique.MySQLContainerTestConfiguration;
import esportsclash.pratique.auth.application.infrastructure.spring.LoginDTO;
import esportsclash.pratique.auth.application.infrastructure.spring.RegisterDTO;
import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.auth.domain.viewModel.LoggedInUserViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(MySQLContainerTestConfiguration.class)
public class LoginE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHasher passwordHasher;

    @BeforeEach
    public void setup(){
        userRepository.clear();
        var user = new User(
                "123",
                "test@yahoo.fr",
                passwordHasher.hash("password"));
        userRepository.save(user);
    }

    @Test
    public void shouldLogTheUserIn() throws Exception {

        var loginDto = new LoginDTO("test@yahoo.fr", "password");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andReturn();

       LoggedInUserViewModel viewModel = objectMapper.readValue(
               result.getResponse().getContentAsString(),
               LoggedInUserViewModel.class
       );

       Assert.assertEquals("123", viewModel.getId());
       Assert.assertEquals("test@yahoo.fr", viewModel.getEmailAddress());

    }

    @Test
    public void whenEmailAddressIsUnvailable_shouldThrow() throws Exception {

        var existingUser = new User("1234", "test@yahoo.fr", "password");
        userRepository.save(existingUser);

        var registerDto = new  RegisterDTO(existingUser.getEmailAddress(), "password");
         mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
