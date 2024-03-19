package esportsclash.pratique.auth;

import esportsclash.pratique.auth.application.services.passwordHasher.BcryptPasswordHasher;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PasswordHasherTests {
    public PasswordHasher createHasher(){
        return new BcryptPasswordHasher();
    }

    @Test
    public void shouldHashPassword() {
        var hasher = createHasher();

        var clearPassword = "azerty";
        var hashedPassword  = hasher.hash(clearPassword);

        // Vérifier la correspondance du mot de passe en utilisant la méthode réelle
        boolean isMatch = hasher.match(clearPassword, hashedPassword);

        // Maintenant, affirmer le résultat booléen de la correspondance du mot de passe
        Assert.assertTrue(isMatch);
    }

}
