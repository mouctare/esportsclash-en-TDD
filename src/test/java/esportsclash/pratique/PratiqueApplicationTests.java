package esportsclash.pratique;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(MySQLContainerTestConfiguration.class)
class PratiqueApplicationTests {

	@Test
	void contextLoads() {
	}

}
