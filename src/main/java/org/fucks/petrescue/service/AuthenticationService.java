/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.service;

import org.fucks.petrescue.entity.person.Credential;
import org.fucks.petrescue.repository.person.ICredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author fucks
 */
@Service
public class AuthenticationService  implements UserDetailsService{

    @Autowired
    private ICredentialRepository personCredentialRepository;
    
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
                
        var usernameUserExample = Example.of(
                new Credential(usernameOrEmail, null, usernameOrEmail),
                ExampleMatcher.matchingAny());
        
        var credential = this.personCredentialRepository.findOne(usernameUserExample);
        
        if(!credential.isPresent())
            throw new UsernameNotFoundException(String.format("User with username or email %s not found", usernameOrEmail));
        
        return credential.get();
    }
    
    
}
