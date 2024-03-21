package esportsclash.pratique.auth;

import esportsclash.pratique.auth.application.services.jwtservice.ConcreteJwtService;
import esportsclash.pratique.auth.domain.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class JwtServiceTests {
    @Test
    void shouldTokenizeTheUser(){
        var jwtService = new ConcreteJwtService("sooper_sekret_please_do_not_share", 60);
        var user = new User("123", "toto@yahoo.fr", "azerty");

        var token = jwtService.tokenize(user);
        var authUser = jwtService.parse(token);

        Assert.assertNotNull(authUser);
        Assert.assertEquals(user.getId(), authUser.getId());
        Assert.assertEquals(user.getEmailAddress(), authUser.getEmailAddress());
    }

}
