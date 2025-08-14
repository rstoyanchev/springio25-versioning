package versioning;

import java.math.BigDecimal;
import java.util.Map;

import versioning.repository.Account;
import versioning.repository.AccountRepository;
import versioning.repository.Statement;
import versioning.repository.Statement.Type;
import versioning.repository.StatementRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootApplication
public class VersionServerApp {

	public static void main(String[] args) {
		SpringApplication.run(VersionServerApp.class, args);
	}

	@Bean
	AccountRepository accountRepository() {
		return new AccountRepository(Map.of("99", new Account("99", "Checking")));
	}

	@Bean
	StatementRepository statementRepository() {
		MultiValueMap<String, Statement> statements = new LinkedMultiValueMap<>();
		statements.add("99", new Statement(new BigDecimal(250), Type.CREDIT));
		statements.add("99", new Statement(new BigDecimal(110), Type.DEBIT));
		return new StatementRepository(statements);
	}
}
