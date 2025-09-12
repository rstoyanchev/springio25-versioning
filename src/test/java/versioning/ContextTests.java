package versioning;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.web.client.ApiVersionInserter;

@SpringBootTest
@AutoConfigureMockMvc
class ContextTests {

	@Test
	void getAccount(@Autowired MockMvcTester tester) {
		tester.get().uri("/accounts/99")
				.apiVersion(1.1)
				.assertThat()
				.hasStatusOk()
				.bodyJson().isEqualTo("{\"id\":\"99\",\"name\":\"Checking\"}");
	}

	@TestConfiguration
	static class TestConfig implements MockMvcBuilderCustomizer {

		@Override
		public void customize(ConfigurableMockMvcBuilder<?> builder) {
			builder.apiVersionInserter(ApiVersionInserter.useHeader("X-API-Version"))
					.alwaysDo(MockMvcResultHandlers.print());
		}
	}

}
