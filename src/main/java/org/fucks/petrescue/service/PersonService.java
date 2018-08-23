/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.service;

import org.fucks.petrescue.entity.person.Credential;
import org.fucks.petrescue.repository.person.ICredentialRepository;
import org.fucks.petrescue.repository.person.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author fucks
 */
@Service
public class PersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ICredentialRepository personCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Credential create(String username, String email, String password) {

        Credential credentials = new Credential(
                username,
                passwordEncoder.encode(password),
                email);

        return this.personCredentialRepository.save(credentials);
    }
}
