package org.fucks.petrescue.service;

import org.fucks.petrescue.repository.announce.IAnnounceRepository;
import org.fucks.petrescue.repository.person.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnounceService {

    @Autowired
    private IAnnounceRepository announceRepository;

    @Autowired
    private IPersonRepository personRepository;

}
