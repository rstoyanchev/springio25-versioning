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

package versioning.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import versioning.repository.Account;

import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;

public class VersionClientApp {

	private static final Logger logger = LogManager.getLogger(VersionClientApp.class);

	public static void main(String[] args) {

		RestClient client = RestClient.builder()
				.baseUrl("http://localhost:8080")
				.apiVersionInserter(ApiVersionInserter.useHeader("X-API-Version"))
				.requestInterceptor(new LoggingInterceptor())
				.build();

		Account account = client.get().uri("/accounts/1")
				.apiVersion(1.1)
				.retrieve()
				.body(Account.class);

		logger.info(account);
	}

}
