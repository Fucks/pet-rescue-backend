package org.fucks.petrescue.web.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class EncodedPhotoModel {

    private String filename;

    @Getter
    @Setter
    private String mimetype;

    @Getter
    @Setter
    private String data;
}