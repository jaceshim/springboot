package jace.shim.commons.exception;

/**
 * Created by jaceshim on 2017. 2. 6..
 */
public class UserNameDuplicatedException extends RuntimeException {
	private String userName;
	public UserNameDuplicatedException(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
}
