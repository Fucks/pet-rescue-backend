package org.fucks.petrescue.entity;

import java.io.Serializable;

/**
 *
 * @author Wellington Felipe Fucks
 *
 * @version 1.0
 * @since 1.0, 15/09/2017
 */
public interface IEntity<String extends Serializable> extends Serializable {

    String getId();
}
