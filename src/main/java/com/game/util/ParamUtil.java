package com.game.util;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.Date;

public final class ParamUtil {

    public static boolean isValidId(String value) {
        int id;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return id > 0;
    }

    public static void copyFields(Player player, PlayerDTO playerDTO) {
        player.setName(playerDTO.getName() == null ? player.getName() : playerDTO.getName());
        player.setTitle(playerDTO.getTitle() == null ? player.getTitle() : playerDTO.getTitle());
        player.setRace(playerDTO.getRace() == null ? player.getRace() : playerDTO.getRace());
        player.setProfession(playerDTO.getProfession() == null ? player.getProfession() : playerDTO.getProfession());
        player.setBirthday(playerDTO.getBirthday() == null ? player.getBirthday() : playerDTO.getBirthday());
        player.setBanned(playerDTO.getBanned() == null ? player.getBanned() : playerDTO.getBanned());
        Integer exp = playerDTO.getExperience();
        if (exp != null) {
            player.setExperience(exp);
            player.setUntilNextLevel(getUntilNextLevelByExp(exp));
            player.setLevel(getLevelByExp(exp));
        }
    }

    public static boolean isValidParamsForUpdate(PlayerDTO playerDTO) {
        String name = playerDTO.getName();
        String title = playerDTO.getTitle();
        Integer exp = playerDTO.getExperience();
        Date birthday = playerDTO.getBirthday();
        boolean result = name == null || (!name.isEmpty() && name.length() < 13);
        result = result && (title == null || (!title.isEmpty() && title.length() < 31));
        result = result && (exp == null || exp > 0 && exp <= 10000000);
        result = result && (birthday == null || (birthday.getTime() >= 946684800000L && birthday.getTime() <= 32503680000000L));
        return result;
    }

    public static boolean isValidParamsForCreate(PlayerDTO playerDTO) {
        String name = playerDTO.getName();
        String title = playerDTO.getTitle();
        Race race = playerDTO.getRace();
        Profession profession = playerDTO.getProfession();
        Date birthday = playerDTO.getBirthday();
        Integer exp = playerDTO.getExperience();
        boolean result = name != null && title != null && race != null && profession != null && birthday != null && exp != null;
        result = result && !name.isEmpty() && name.length() < 13;
        result = result && !title.isEmpty() && title.length() < 31;
        result = result && exp > 0 && exp <= 10000000;
        result = result && birthday.getTime() >= 946684800000L && birthday.getTime() <= 32503680000000L;
        return result;
    }

    public static int getLevelByExp(Integer exp) {
        return (int) ((Math.sqrt(2500 + 200 * exp) - 50) / 100);
    }

    public static int getUntilNextLevelByExp(Integer experience) {
        return 50 * (getLevelByExp(experience) + 1) * (getLevelByExp(experience) + 2) - experience;
    }
}
