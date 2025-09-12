/*
 * Copyright 2002-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package versioning;

import org.junit.jupiter.api.Test;
import versioning.repository.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.test.client.reactive.WebTestClientBuilderCustomizer;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.ApiVersionInserter;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientTests {

	@Test
	void getAccount(@Autowired WebTestClient webTestClient) {
		webTestClient.get().uri("/accounts/99")
				.apiVersion(1.1)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Account.class).isEqualTo(new Account("99", "Checking"));
	}

	@TestConfiguration
	static class TestConfig implements WebTestClientBuilderCustomizer {

		@Override
		public void customize(WebTestClient.Builder builder) {
			builder.apiVersionInserter(ApiVersionInserter.useHeader("X-API-Version"));
		}
	}

}
