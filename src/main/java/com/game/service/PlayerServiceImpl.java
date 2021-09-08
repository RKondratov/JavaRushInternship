package com.game.service;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.game.util.ParamUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("playerService")
public final class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player findPlayerById(String id) {
        Optional<Player> player = playerRepository.findById(Long.parseLong(id));
        return player.get();
    }

    public List<Player> getAllPlayersOnPageAndSortByAttr(PlayerDTO playerDTO,
                                                         Integer pageNo,
                                                         Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(playerDTO.getSortBy().getFieldName()));
        return playerRepository.getAllPlayersOnPageAndSortByAttr(playerDTO, paging);
    }

    public Integer getPlayerCount(PlayerDTO playerDTO) {
        return playerRepository.getAllPlayersOnPageAndSortByAttr(playerDTO, null).size();
    }

    public void deleteById(String id) {
        playerRepository.deleteById(Long.parseLong(id));
    }

    public Player updateById(String id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(Long.valueOf(id)).get();
        ParamUtil.copyFields(player, playerDTO);
        return playerRepository.save(player);
    }

    @Override
    public Player createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        ParamUtil.copyFields(player, playerDTO);
        return playerRepository.save(player);
    }
}
