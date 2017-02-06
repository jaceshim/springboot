package jace.shim.account

import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by jaceshim on 2017. 2. 3..
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerSpec extends Specification {

	@Autowired
	WebApplicationContext wac

	@Autowired
	ModelMapper modelMapper

	@Autowired
	ObjectMapper objectMapper

	MockMvc mockMvc

	def setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
	}

	def "/hello를 호출하면 응답상태 200과 'hello'를 반환한다"() {
		when:
		def result = mockMvc.perform(get("/hello"))
		then:

		result.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("hello"))
	}

	def "CreateAccount()를 호출시 username이 빈문자열인 경우 ErrorResponse를 반환한다"() {
		given:
		AccountDto.Create createDto = new AccountDto.Create()
		createDto.setUserName("jaceshim")
		createDto.setPassword("1234")

		when:
		def response = mockMvc.perform(post("/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createDto)))
		then:
		response.andDo(print())
		response.andExpect(status().isBadRequest())
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
