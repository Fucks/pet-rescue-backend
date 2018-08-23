/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.config.data;

import com.mongodb.client.model.geojson.Position;
import org.fucks.petrescue.entity.announce.Announce;
import org.fucks.petrescue.entity.person.Credential;
import org.fucks.petrescue.entity.person.Person;
import org.fucks.petrescue.repository.announce.IAnnounceRepository;
import org.fucks.petrescue.repository.person.ICredentialRepository;
import org.fucks.petrescue.repository.person.IPersonRepository;
import org.fucks.petrescue.service.PersonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author fucks
 */
public class Bootstrap implements InitializingBean{

    Person person = null;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IAnnounceRepository announceRepository;

    @Autowired
    private ICredentialRepository personCredentialRepository;

    @Autowired
    private PersonService personService;

    @Override
    public void afterPropertiesSet() {
        createDefaultUser();
    }
    
    private void createDefaultUser(){

        if(this.personRepository.count() > 0)
            return;

        Credential credentials = personService.create("fucks", "fucks@fucks", "fucks");

        credentials = personCredentialRepository.save(credentials);

        person = new Person(
                "Wellington",
                "fucks@fucks",
                "99883222",
                credentials);

        person = this.personRepository.insert(person);
        this.personCredentialRepository.save(credentials);

        bootstrapAnnounces();
    }

    private void bootstrapAnnounces() {
        Announce announce1 = new Announce(
                "Anuncio 1",
                "Descrição",
                person,
                null,
                null,
                new Point(-25.49348,-54.53045)
        );

        Announce announce2 = new Announce(
                "Anuncio 2",
                "Descrição",
                person,
                null,
                null,
                new Point(-25.49254,-54.53181)
        );

        Announce announce3 = new Announce(
                "Anuncio 3",
                "Descrição",
                person,
                null,
                null,
                new Point(-25.49195,-54.53243)
        );

        Announce announce4 = new Announce(
                "Anuncio 4",
                "Descrição",
                person,
                null,
                null,
                new Point(-25.49271,-54.53548)
        );

        this.announceRepository.saveAll(Arrays.asList(announce1, announce2, announce3, announce4));
    }

    
}
