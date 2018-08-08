/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.web.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

/**
 *
 * @author fucks
 */
@JsonAutoDetect
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PersonWebModel {
    
    @Getter
    @Setter
    private String name;
    
    
    @Getter
    @Setter
    private String username;
    
    @Getter
    @Setter
    private String phone;
    
    @Getter
    @Setter
    private String email;
    
    @Getter
    @Setter
    private String password;
    
    @Getter
    @Setter
    private double latitude;
    
    @Getter
    @Setter
    private double longitude;
}
