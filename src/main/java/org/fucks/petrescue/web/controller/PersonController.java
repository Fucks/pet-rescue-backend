/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.web.controller;

import org.fucks.petrescue.entity.person.Person;
import org.fucks.petrescue.entity.person.Credential;
import org.fucks.petrescue.repository.person.ICredentialRepository;
import org.fucks.petrescue.repository.person.IPersonRepository;
import org.fucks.petrescue.service.PersonService;
import org.fucks.petrescue.web.models.PersonWebModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author fucks
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ICredentialRepository personCredentialRepository;

    @Autowired
    private PersonService personService;

    @PostMapping("register")
    public ResponseEntity create(@RequestBody PersonWebModel model) {

        Person person;
        Credential credential = null;

        try {

            credential = personService.create(model.getUsername(), model.getEmail(), model.getPassword());

            person = new Person(
                    model.getName(),
                    model.getEmail(),
                    model.getPhone(),
                    credential);

            person = this.personRepository.insert(person);

            return ResponseEntity
                    .ok(person);

        } catch (Exception e) {

            if (credential != null) {
                this.personCredentialRepository.delete(credential);
            }

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("list")
    public Page get(Pageable pageable) {

        return this.personRepository.findAll(pageable);
    }

    @GetMapping("me")
    public ResponseEntity authUser() {
        Credential authenticated = (Credential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Person> personWithCredentials = this.personRepository.findPersonByCredentialId(authenticated.getId());

        if(!personWithCredentials.isPresent())
            return ResponseEntity
                    .notFound()
                    .build();

        return ResponseEntity
                .ok(personWithCredentials.get());
    }
}
