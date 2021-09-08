package com.game.controller;

import com.game.dto.PlayerDTO;
import com.game.dto.PlayerUpdateDTO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import com.game.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/players")
public final class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayersList(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String title,
                                                       @RequestParam(required = false) Race race,
                                                       @RequestParam(required = false) Profession profession,
                                                       @RequestParam(required = false) Long after,
                                                       @RequestParam(required = false) Long before,
                                                       @RequestParam(required = false) Boolean banned,
                                                       @RequestParam(required = false) Integer minExperience,
                                                       @RequestParam(required = false) Integer maxExperience,
                                                       @RequestParam(required = false) Integer minLevel,
                                                       @RequestParam(required = false) Integer maxLevel,
                                                       @RequestParam(required = false) PlayerOrder order,
                                                       @RequestParam(required = false) Integer pageNumber,
                                                       @RequestParam(required = false) Integer pageSize) {
        pageNumber = pageNumber == null ? 0 : pageNumber;
        pageSize = pageSize == null ? 3 : pageSize;
        return new ResponseEntity<>(
                playerService.getAllPlayersOnPageAndSortByAttr(
                        new PlayerDTO(order, name, title, race, profession, after, before, banned, minExperience,
                                maxExperience, minLevel, maxLevel),
                        pageNumber,
                        pageSize), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getPlayersCount(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String title,
                                                   @RequestParam(required = false) Race race,
                                                   @RequestParam(required = false) Profession profession,
                                                   @RequestParam(required = false) Long after,
                                                   @RequestParam(required = false) Long before,
                                                   @RequestParam(required = false) Boolean banned,
                                                   @RequestParam(required = false) Integer minExperience,
                                                   @RequestParam(required = false) Integer maxExperience,
                                                   @RequestParam(required = false) Integer minLevel,
                                                   @RequestParam(required = false) Integer maxLevel) {
        return new ResponseEntity<>(
                playerService.getPlayerCount(
                        new PlayerDTO(null, name, title, race, profession, after, before, banned, minExperience,
                                maxExperience, minLevel, maxLevel)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable("id") String id) {
        if (!ParamUtil.isValidId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Player player = playerService.findPlayerById(id);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable("id") String id) {
        if (!ParamUtil.isValidId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            playerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") String id,
                                               @RequestBody PlayerUpdateDTO playerUpdateDTO) {
        PlayerDTO playerDTO = PlayerDTO.toPlayerForUpdate(playerUpdateDTO);
        if (!ParamUtil.isValidId(id) || !ParamUtil.isValidParamsForUpdate(playerDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(playerService.updateById(id, playerDTO), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerUpdateDTO playerUpdateDTO) {
        PlayerDTO playerDTO = PlayerDTO.toPlayerForCreate(playerUpdateDTO);
        if (!ParamUtil.isValidParamsForCreate(playerDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(playerService.createPlayer(playerDTO), HttpStatus.OK);
    }
}
