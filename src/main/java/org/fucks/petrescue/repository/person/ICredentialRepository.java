/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.repository.person;

import org.fucks.petrescue.entity.person.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *
 * @author fucks
 */
@Repository
public interface ICredentialRepository extends MongoRepository<Credential, UUID>{
    
}
