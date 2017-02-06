package jace.shim.account;

import jace.shim.commons.exception.UserNameDuplicatedException;
import org.modelmapper.ModelMapper;
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

	@Autowired
	private ModelMapper modelMapper;

	public Account createAccount(AccountDto.Create createDto) {
		Account account = modelMapper.map(createDto, Account.class);

		String userName = createDto.getUserName();
		if (accountRepository.findByUserName(userName) != null) {
			throw new UserNameDuplicatedException(userName);
		}

		LocalDateTime now = LocalDateTime.now();
		account.setCreated(now);
		account.setUpdated(now);

		return accountRepository.save(account);
	}
}
