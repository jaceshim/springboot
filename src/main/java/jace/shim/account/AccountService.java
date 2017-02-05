package jace.shim.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by jaceshim on 2017. 2. 3..
 */
@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(AccountDto.Create createDto) {
		Account account = new Account();
		account.setUserName(createDto.getUserName());
		account.setPassword(createDto.getPassword());
		LocalDateTime now = LocalDateTime.now();
		account.setCreated(now);
		account.setUpdated(now);

		return accountRepository.save(account);
	}
}
