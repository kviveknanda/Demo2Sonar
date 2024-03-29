package webapp.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import webapp.tier.db.mysql.InsertMysql;
import webapp.tier.db.mysql.SelectMysql;
import webapp.tier.mq.rabbitmq.GetRabbitmq;

@ExtendWith(MockitoExtension.class)
public class ApplicationControllerTest {

	private MockMvc mockMvc;

	@Mock
	InsertMysql insmysql;
	@Mock
	SelectMysql selmysql;
	@Mock
	GetRabbitmq getrab;
	@InjectMocks
	ApplicationController appcont;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(appcont).build();
	}

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void testInsertDbError() throws Exception {
		try {
			mockMvc.perform(get("/InsertMysql"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

	@Test
	public void testSelectMysqlError() throws Exception {
		try {
			mockMvc.perform(get("/SelectMysql"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

	@Test
	public void testDeleteMysqlError() throws Exception {
		try {
			mockMvc.perform(get("/DeleteMysql"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

	@Test
	public void testGetMqError() throws Exception {
		try {
			mockMvc.perform(get("/GetRabbitmq"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class java.net.UnknownHostException"));
		}
	}

	@Test
	public void testPutMqError() {
		try {
			mockMvc.perform(get("/PutRabbitmq"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class java.net.UnknownHostException"));
		}
	}

	@Test
	public void testPutMqBatchError() {
		try {
			mockMvc.perform(get("/PutRabbitmqConsumer"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class java.net.UnknownHostException"));
		}
	}

	@Test
	public void testGetMemcached() {
		try {
			mockMvc.perform(get("/GetMemcached"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

	@Test
	public void testSetMemcached() {
		try {
			mockMvc.perform(get("/SetMemcached"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

	@Test
	public void testGetRedis() {
		try {
			mockMvc.perform(get("/GetRedis"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

	@Test
	public void testSetRedis() {
		try {
			mockMvc.perform(get("/SetRedis"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

	@Test
	public void testPublishRedis() {
		try {
			mockMvc.perform(get("/PublishRedis"));
			fail();
		} catch (Exception expected) {
			assertThat(expected.getClass().toString(), is("class org.springframework.web.util.NestedServletException"));
		}
	}

}
