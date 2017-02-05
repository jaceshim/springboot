package jace.shim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by jaceshim on 2017. 2. 5..
 */
@Data
@Builder
//@AllArgsConstructor
public class Person {
	private final String firstName;
	private final String lastName;
	private Integer age;
	private String email;
}
