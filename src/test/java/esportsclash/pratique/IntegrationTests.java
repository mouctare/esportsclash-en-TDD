package esportsclash.pratique;

import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.services.jwtservice.JwtService;
import esportsclash.pratique.auth.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MySQLContainerTestConfiguration.class)
@Transactional
public class IntegrationTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected JwtService jwtService;

    protected String createJWT() {
        var email = "toto@yahoo.fr";
        var user = userRepository.findByEmailAddress(email).orElse(null);
        if (user == null) {
            // User not found, create a new one (handle password hashing here)
            user = new User("012", email, "hashedPassword");
            userRepository.save(user);
        }
        return "Bearer " + jwtService.tokenize(user);
    }

}
