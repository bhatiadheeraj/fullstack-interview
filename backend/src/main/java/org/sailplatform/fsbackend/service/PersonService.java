package org.sailplatform.fsbackend.service;

import java.util.List;

import org.sailplatform.fsbackend.model.Person;
import org.sailplatform.fsbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person add(Person toAdd){
        return personRepository.save(toAdd);
    }

    public Person getByID(Long id) {
        return personRepository.findById(id).get();
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public void update(Person update, Long id) {
        personRepository.save(update);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
