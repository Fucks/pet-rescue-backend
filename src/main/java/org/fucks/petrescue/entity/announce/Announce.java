package org.fucks.petrescue.entity.announce;

import com.mongodb.client.model.geojson.Position;
import lombok.*;
import org.fucks.petrescue.entity.AbstractEntity;
import org.fucks.petrescue.entity.person.Person;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Announce extends AbstractEntity {

    @Getter
    @Setter
    @Field
    @Indexed
    private String title;

    @Getter
    @Setter
    @Field
    @Indexed
    private String description;

    @Getter
    @Setter
    @Field
    @Indexed
    @DBRef(lazy = true)
    private Person person;

    @Getter
    @Setter
    @Field
    private String tumbnail;

    @Getter
    @Setter
    @Field
    private List<String> photos;

    @Getter
    @Setter
    @Field
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point position; //position using latitude and longitude

}
