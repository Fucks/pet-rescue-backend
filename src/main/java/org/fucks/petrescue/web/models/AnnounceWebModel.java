package org.fucks.petrescue.web.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.fucks.petrescue.entity.announce.AnnounceSpecie;
import org.fucks.petrescue.entity.announce.AnnounceType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnounceWebModel {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private AnnounceType type;

    @Getter
    @Setter
    private AnnounceSpecie specie;

    @Getter
    @Setter
    private double latitude;

    @Getter
    @Setter
    private double longitude;

    @Getter
    @Setter
    private List<EncodedPhotoModel> encodedPhotos;

}
