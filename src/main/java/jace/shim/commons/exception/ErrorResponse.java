package jace.shim.commons.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by jaceshim on 2017. 2. 6..
 */
@Setter
@Getter
public class ErrorResponse {

	private String message;

	private String code;

	private List<FieldError> erros;

	@Getter
	@Setter
	public static class FieldError {
		private String field;
		private String value;
		private String reason;
	}

}
