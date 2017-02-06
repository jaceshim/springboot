package jace.shim.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by jaceshim on 2017. 2. 3..
 */
@Entity
@Getter
@Setter
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String loginId;

	private String userName;

	private String password;

	private String email;

	private LocalDateTime created;

	private LocalDateTime updated;
}
