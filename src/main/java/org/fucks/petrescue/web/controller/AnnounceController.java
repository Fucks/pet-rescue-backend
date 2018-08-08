package org.fucks.petrescue.web.controller;

import com.mongodb.client.model.geojson.Position;
import org.fucks.petrescue.entity.announce.Announce;
import org.fucks.petrescue.entity.person.Credential;
import org.fucks.petrescue.entity.person.Person;
import org.fucks.petrescue.repository.announce.IAnnounceRepository;
import org.fucks.petrescue.repository.person.IPersonRepository;
import org.fucks.petrescue.service.AnnounceService;
import org.fucks.petrescue.service.FileService;
import org.fucks.petrescue.util.Metrics;
import org.fucks.petrescue.web.models.AnnounceWebModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/announce")
public class AnnounceController {

    @Autowired
    private IAnnounceRepository announceRepository;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private AnnounceService announceService;

    @Autowired
    private FileService fileService;

    @PostMapping("")
    public ResponseEntity save(@RequestBody AnnounceWebModel model) {

        var logged = Credential.getLogged();

        if (!logged.isPresent())
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autorizado!");

        var credentials = logged.get();

        var loggedPerson = personRepository.findPersonByCredentialId(credentials.getId()).get();

        var announce = new Announce(
                model.getTitle(),
                model.getDescription(),
                loggedPerson,
                null,
                null,
                new Point(model.getLatitude(),model.getLongitude())
        );

        announce = this.announceRepository.save(announce);

        try {
            var photos = this.fileService.save(model.getEncodedPhotos(), announce);

            announce.setPhotos(
                    photos
                    .stream()
                    .map(p -> p.get("path").toString())
                    .collect(Collectors.toList())
            );

        } catch (Exception e) {
            this.announceRepository.delete(announce);
            e.printStackTrace();
        }

        return ResponseEntity.ok(announce);
    }

    @GetMapping("")
    public ResponseEntity findByLocation(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double distance, Pageable page) {

        return ResponseEntity
                .ok(this
                        .announceRepository
                        .findByPositionNear(
                                new Point(latitude, longitude),
                                new Distance(distance, Metrics.METERS),
                                page));
    }
}
