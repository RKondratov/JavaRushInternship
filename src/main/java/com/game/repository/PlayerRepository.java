package com.game.repository;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
    @Query("SELECT p FROM Player p " +
            "WHERE (:#{#player.name} IS NULL OR p.name LIKE CONCAT('%', :#{#player.name}, '%')) " +
            "AND (:#{#player.title} IS NULL OR p.title LIKE CONCAT('%',:#{#player.title},'%')) " +
            "AND (:#{#player.race} IS NULL OR p.race = :#{#player.race} " +
            "AND (:#{#player.profession} IS NULL OR p.profession = :#{#player.profession})) " +
            "AND (:#{#player.banned} IS NULL OR p.banned = :#{#player.banned}) " +
            "AND (:#{#player.minExperience} IS NULL OR p.experience >= :#{#player.minExperience}) " +
            "AND (:#{#player.maxExperience} IS NULL OR p.experience <= :#{#player.maxExperience}) " +
            "AND (:#{#player.minLevel} IS NULL OR p.level >= :#{#player.minLevel}) " +
            "AND (:#{#player.maxLevel} IS NULL OR p.level <= :#{#player.maxLevel}) " +
            "AND (:#{#player.after} IS NULL OR p.birthday >= :#{#player.after}) " +
            "AND (:#{#player.before} IS NULL OR p.birthday <= :#{#player.before}) ")
    List<Player> getAllPlayersOnPageAndSortByAttr(@Param("player") PlayerDTO player, Pageable pageable);
}
