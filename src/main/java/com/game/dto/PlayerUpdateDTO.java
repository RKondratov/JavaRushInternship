package com.game.dto;

import com.game.entity.Profession;
import com.game.entity.Race;

public final class PlayerUpdateDTO extends PlayerDTO {
    public PlayerUpdateDTO() {
    }

    public PlayerUpdateDTO(String name, String title, Race race, Profession profession, Boolean banned, Long birthday, Integer experience) {
        super(name, title, race, profession, banned, birthday, experience);
    }
}
