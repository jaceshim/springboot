package jace.shim.account

import spock.lang.Specification

/**
 * Created by jaceshim on 2017. 2. 3..
 */
class AccountTest extends Specification {


	def "Account Setter, Getter 테스트" () {
		given:
			Account account = new Account()
			def jaceshim = "jaceshim"
		when:
		account.setUserName(jaceshim)
		account.setPassword("1234")
		then:
			account.getUserName() == jaceshim

	}

}
