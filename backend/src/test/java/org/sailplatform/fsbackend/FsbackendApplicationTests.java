package org.sailplatform.fsbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import org.sailplatform.fsbackend.model.Person;
;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FsbackendApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {
	}

	@Test
     public void testGetAllPerson() {
     HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/all",
        HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }


    @Test
    public void testCreatePerson() {
        Person person = new Person();
        person.setId(Long.MAX_VALUE);
        person.setFirstName("admin");
        person.setLastName("admin");
        Person response = restTemplate.postForObject(getRootUrl() + "/add", person, Person.class);
		assertEquals(response.getFirstName(),response.getFirstName());
		assertEquals(response.getFirstName(),response.getLastName());
    }

    @Test
    public void testUpdatePerson() {
		Person person = new Person();
        person.setId(Long.MAX_VALUE);
        person.setFirstName("admin1");
        person.setLastName("admin2");

        Person postResponse = restTemplate.postForObject(getRootUrl() + "/add", person, Person.class);
        postResponse.setFirstName("adminChanged");
        postResponse.setLastName("adminLastNameChanged");
        restTemplate.put(getRootUrl() + "/modify/"+postResponse.id, postResponse);

        Person updatedPerson = restTemplate.getForObject(getRootUrl() + "/" + postResponse.id, Person.class);
		assertEquals(updatedPerson.getFirstName(),"adminChanged");
		assertEquals(updatedPerson.getLastName(),"adminLastNameChanged");
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person();
        person.setId(Long.MAX_VALUE);
        person.setFirstName("admin1");
        person.setLastName("admin2");

        Person postResponse = restTemplate.postForObject(getRootUrl() + "/add", person, Person.class);
		assertNotNull(postResponse);
        restTemplate.delete(getRootUrl() + "/delete/" + postResponse.id);
         try {
            person = restTemplate.getForObject(getRootUrl() + "/" + postResponse.id, Person.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
}
