package org.fucks.petrescue.repository.announce;

import org.fucks.petrescue.entity.announce.Announce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository()
public interface IAnnounceRepository extends MongoRepository<Announce, UUID> {

    public GeoPage<Announce> findByPositionNear(Point point, Distance distance, Pageable page);
}
