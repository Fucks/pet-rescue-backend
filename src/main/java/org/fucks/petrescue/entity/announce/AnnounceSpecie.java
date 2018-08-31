package org.fucks.petrescue.entity.announce;

import lombok.Getter;

public enum AnnounceSpecie {
    DOG("Dog"),
    CAT("Cat");

    @Getter
    private final String name;

    AnnounceSpecie(String name) {
        this.name = name;
    }
}
