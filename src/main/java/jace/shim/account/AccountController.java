package jace.shim.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by jaceshim on 2017. 2. 3..
 */
@RestController
@Slf4j
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create createDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(System.out::println);

			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		log.debug("create dto : {}", createDto.toString());

		Account newAccount = accountService.createAccount(createDto);

		return new ResponseEntity(newAccount, HttpStatus.CREATED);
	}
}
