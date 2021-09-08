package com.game.service;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;

import java.util.List;

public interface PlayerService {
    Player findPlayerById(String id);

    List<Player> getAllPlayersOnPageAndSortByAttr(PlayerDTO playerDTO, Integer pageNumber, Integer pageSize);

    Integer getPlayerCount(PlayerDTO playerDTO);

    void deleteById(String id);

    Player updateById(String id, PlayerDTO playerDTO);

    Player createPlayer(PlayerDTO playerDTO);
}
