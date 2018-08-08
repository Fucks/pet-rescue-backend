/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.entity.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.fucks.petrescue.entity.AbstractEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author fucks
 */
@Document
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AbstractEntity {
    
    @Getter
    @Setter
    @Field
    private String name;
    
    @Getter
    @Setter
    @Field
    private String email;
    
    @Getter
    @Setter
    @Field
    private String phone;
    
    @Getter
    @Setter
    @Field
    @JsonIgnore
    @DBRef(lazy =  true)
    private Credential credential;

    /**
     * @param id
     */
    public Person(String id) {
        super(id);
    }
}
