package jace.shim.account

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*

/**
 * Created by jaceshim on 2017. 2. 3..
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AccountControllerSpec extends Specification {

	MockMvc mockMvc

	ObjectMapper objectMapper

	def setup() {
		def controller = new AccountController()
		controller.accountService = Mock(AccountService.class)
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build()\

		objectMapper = new ObjectMapper()
	}

	def "/hello를 호출하면 응답상태 200과 'hello'를 반환한다"() {
		when:
		def result = mockMvc.perform(get("/hello"))
		then:

		result.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("hello"))
	}

	def "/accounts - CreateAccount() 를 호출하면 응답상태 201를 반환한다"() {
		given:
		AccountDto.Create createDto = new AccountDto.Create()
		createDto.setUserName("jaceshim")
		createDto.setPassword("12345")

		when:
		def response = mockMvc.perform(post("/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createDto)))
		then:
		response.andDo(print())
		response.andExpect(status().isCreated())
	}
}
