package com.scores.application.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.data.rq.AddUserRQ;
import com.scores.domain.controller.data.rs.UsersRS;
import com.scores.domain.dto.data.UserDataDTO;
import com.scores.domain.ports.data.CrudUserPort;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CrudUserServiceTest {

	@InjectMocks
	private CrudUserService crudUserService;

	@Mock
	private CrudUserPort crudUserPort;

	AddUserRQ addUserRQ;
	UserDataDTO userDataDTO;
	UserDataDTO userDataDTOResponseAdd;

	List<UserDataDTO> userDataDTOList;
	List<UsersRS> userRSList;

	@BeforeEach
	void setUp() {

		addUserRQ = AddUserRQ.builder()
			.userCode("1234LL")
			.name("Prueba")
			.company("Company")
			.job("developer")
			.build();

		userDataDTOResponseAdd = UserDataDTO.builder()
			.userCode("1234LL")
			.name("Prueba")
			.company("Company")
			.job("developer")
			.build();

		userDataDTO = UserDataDTO.builder()
			.userCode("1234LL")
			.name("Prueba")
			.company("Company")
			.job("developer")
			.score(8)
			.achievement("sin logro")
			.build();

		userRSList = List.of(UsersRS.builder()
								 .userCode("1234LL")
								 .name("Prueba")
								 .score(8)
								 .achievement("sin logro")
								 .build());

		userDataDTOList = List.of(userDataDTO);
	}

	@Test
	void addUser() {

		doNothing().when(crudUserPort).processAddUser(userDataDTOResponseAdd);
		var response = crudUserService.addUser(addUserRQ);

		verify(crudUserPort, times(1)).processAddUser(userDataDTOResponseAdd);
		assertThat(response.getMessage()).isNotNull();
	}

	@Test
	void getUser() throws Exception {

		given(crudUserPort.processGetUser("1234LL")).willReturn(userDataDTO);

		var response = crudUserService.getUser("1234LL");

		assertThat(response.getUser()).isNotNull();
		assertThat(response.getMessage()).isNotNull();

		assertThat(response.getUser().getName()).isNotNull();
		assertThat(response.getUser().getCompany()).isNotNull();
		assertThat(response.getUser().getJob()).isNotNull();
		assertThat(response.getUser().getScore()).isNotNull();
		assertThat(response.getUser().getAchievement()).isNotNull();
		assertThat(response.getMessage())
			.isEqualTo("Successfully get user");

	}

	@Test
	void getUser_Exception() throws Exception {

		given(crudUserPort.processGetUser("1234LL")).willThrow(Exception.class);

		final MessageRS messageRS = crudUserService.getUser("1234LL");

		assertThat(messageRS.getUser()).isNull();
		assertThat(messageRS.getMessage()).contains("Throw Exception: ");
	}

	@Test
	void getUsers() {

		given(crudUserPort.processGetUsers()).willReturn(userDataDTOList);

		final var response = crudUserService.getUsers();

		assertThat(response.size()).isEqualTo(1);
		assertThat(response.get(0).getUserCode()).isNotNull();
		assertThat(response.get(0).getName()).isNotNull();
		assertThat(response.get(0).getScore()).isNotNull();
		assertThat(response.get(0).getAchievement()).isNotNull();
	}

	@Test
	void getUsers_Empty() {

		given(crudUserPort.processGetUsers()).willReturn(Collections.emptyList());

		final var response = crudUserService.getUsers();

		assertThat(response.size()).isEqualTo(0);
	}
}