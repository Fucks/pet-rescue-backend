package org.fucks.petrescue.entity.announce;

import lombok.Getter;

public enum AnnounceType {
    LOST("Lost"), FOUND("Found");

    @Getter
    private final String name;

    AnnounceType(String name) {
        this.name = name;
    }

}
