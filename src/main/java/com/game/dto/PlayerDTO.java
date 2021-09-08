package com.game.dto;

import com.game.controller.PlayerOrder;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.Date;

public class PlayerDTO {
    private PlayerOrder sortBy;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Date after;
    private Date before;
    private Boolean banned;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minLevel;
    private Integer maxLevel;
    private Date birthday;
    private Integer experience;

    public PlayerDTO(PlayerOrder sortBy, String name, String title, Race race, Profession profession, Long after, Long before, Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel) {
        this.sortBy = sortBy == null ? PlayerOrder.ID : sortBy;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.after = after == null ? null : new Date(after);
        this.before = before == null ? null : new Date(before);
        this.banned = banned;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public PlayerDTO(String name, String title, Race race, Profession profession, Boolean banned, Long birthday, Integer experience) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.banned = banned;
        this.birthday = birthday == null ? null : new Date(birthday);
        this.experience = experience;
    }

    public PlayerDTO() {
    }

    public PlayerOrder getSortBy() {
        return sortBy;
    }

    public void setSortBy(PlayerOrder sortBy) {
        this.sortBy = sortBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getAfter() {
        return after;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public static PlayerDTO toPlayerForUpdate(PlayerUpdateDTO playerUpdateDTO) {
        PlayerDTO playerDTO = new PlayerDTO(
                playerUpdateDTO.getName(),
                playerUpdateDTO.getTitle(),
                playerUpdateDTO.getRace(),
                playerUpdateDTO.getProfession(),
                playerUpdateDTO.getBanned(),
                playerUpdateDTO.getBirthday() == null ? null : playerUpdateDTO.getBirthday().getTime(),
                playerUpdateDTO.getExperience()
        );
        return playerDTO;
    }

    public static PlayerDTO toPlayerForCreate (PlayerUpdateDTO playerUpdateDTO) {
        Boolean banned = playerUpdateDTO.getBanned() != null && playerUpdateDTO.getBanned();
        PlayerDTO playerDTO = new PlayerDTO(
                playerUpdateDTO.getName(),
                playerUpdateDTO.getTitle(),
                playerUpdateDTO.getRace(),
                playerUpdateDTO.getProfession(),
                banned,
                playerUpdateDTO.getBirthday() == null ? null : playerUpdateDTO.getBirthday().getTime(),
                playerUpdateDTO.getExperience()
        );
        return playerDTO;
    }
}
