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

import versioning.repository.Account;
import versioning.repository.AccountRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountRepository repository;

	public AccountController(AccountRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{id}")
	Account getAccount(@PathVariable String id) {
		return repository.getAccount(id);
	}

	@GetMapping(path = "/{id}", version = "1.1+")
	Account getAccount1_1(@PathVariable String id) {
		return repository.getAccount(id);
	}

}
