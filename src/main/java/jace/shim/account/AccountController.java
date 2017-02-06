package jace.shim.account;

import jace.shim.commons.exception.ErrorResponse;
import jace.shim.commons.exception.UserNameDuplicatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setMessage("잘못된 요청입니다");
			errorResponse.setCode("Bad request");

			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}

		log.debug("create dto : {}", createDto.toString());

		Account newAccount = accountService.createAccount(createDto);

		return new ResponseEntity(newAccount, HttpStatus.CREATED);
	}

	@ExceptionHandler(UserNameDuplicatedException.class)
	public ResponseEntity handleUserDuplicatedException(UserNameDuplicatedException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getUserName() + " : 중복된 사용자명 입니다.");
		errorResponse.setCode("duplicated.userName.exception");

		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);

	}
}
