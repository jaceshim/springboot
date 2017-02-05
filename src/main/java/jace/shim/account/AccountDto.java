package jace.shim.account;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by jaceshim on 2017. 2. 3..
 */
public class AccountDto {

	@Setter
	@Getter
	public static class Create {
		@NotEmpty
		@NotBlank
		@Size(min = 5)
		private String userName;
		@NotEmpty
		@NotBlank
		@Size(min = 5)
		private String password;
	}
}
