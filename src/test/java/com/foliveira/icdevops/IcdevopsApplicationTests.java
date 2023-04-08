package com.foliveira.icdevops;

import org.junit.jupiter.api.Test;

import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class IcdevopsApplicationTests {

	private final String URL_BASE = "http://localhost:";
	private final String END_POINT_ITEM = "/item";
	private final String END_POINT_ACTUATOR = "/actuator/health";
	

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void checaRespostaController() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
			this.URL_BASE + this. port + this.END_POINT_ITEM, Map.class
		);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void checaRespostaActuatorStatus() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
			this.URL_BASE + this.mgt + END_POINT_ACTUATOR, Map.class
		);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
