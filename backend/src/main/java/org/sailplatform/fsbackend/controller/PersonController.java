package org.sailplatform.fsbackend.controller;

import java.util.List;

import org.sailplatform.fsbackend.model.Person;
import org.sailplatform.fsbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = { "*"})
public class PersonController {

    @Autowired PersonService personService;

    @GetMapping("/all")
	public List<Person> getAll() {
		return personService.getAll();
	}

	@GetMapping("/{id}")
	public Person gebyID( @PathVariable Long id) {
		return personService.getByID(id);
	}

    @PostMapping("/add")
	public Person add(Person toAdd) {
		return personService.add(toAdd);
	}

	@PutMapping("/modify/{id}")
	public Person modifyPerson(@RequestBody Person newPerson, @PathVariable Long id) {
		//if (newPerson.getId() != id) ; HAndle Exceptions
		personService.update(newPerson, id);
		return newPerson;
	}

  @DeleteMapping("/delete/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    personService.delete(id);
  }
}
