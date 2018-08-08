package org.fucks.petrescue.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 *
 * @author Wellington Felipe Fucks
 *
 * @version 1.0
 * @since 1.0, 29/10/2015
 */
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public abstract class AbstractEntity implements IEntity<String> {

    @Setter
    @Getter
    @Version
    private Long version;
    
    @Id
    @Setter
    private String id;

    @Setter
    @Getter
    @CreatedDate
    @Field
    private LocalDateTime createdAt;

    @Setter
    @Getter
    @LastModifiedDate
    @Field
    private LocalDateTime updatedAt;

    @Setter
    @Getter
    @CreatedBy
    @Field
    private String createdBy;

    @Setter
    @Getter
    @LastModifiedBy
    @Field
    private String updatedBy;

    /*-------------------------------------------------------------------
     * 		 					CONSTRUCTORS
     *-------------------------------------------------------------------*/

    /**
     *
     * @param id
     */
    public AbstractEntity(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
