package esportsclash.pratique.auth;

import esportsclash.pratique.MySQLContainerTestConfiguration;
import esportsclash.pratique.auth.application.infrastructure.spring.RegisterDTO;
import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import esportsclash.pratique.player.infrastructure.spring.CreatePlayerDTO;
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
public class RegisterE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        userRepository.clear();
    }

    @Test
    public void shouldRegisterUser() throws Exception {

        var registerDto = new RegisterDTO("test@yahoo.fr", "password");

       var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto)))
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andReturn();

       var idResponse = objectMapper.readValue(
               result.getResponse().getContentAsString(),
               IdResponse.class
       );

       var user = userRepository.findById(idResponse.getId()).get();

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmailAddress(), registerDto.getEmailAddress());

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
