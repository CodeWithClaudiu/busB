package com.generation.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.bus.model.Stop;

import jakarta.transaction.Transactional;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {

    List<Stop> findByLineIdOrderByPositionAsc(Long lineId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Stop s SET s.position = s.position + 1 " +
        "WHERE s.line.id = :lineId AND s.position >= :pos")
    void shiftPositionsForward(@Param("lineId") Long lineId, @Param("pos") Integer pos);

    @Modifying
    @Transactional
    @Query("UPDATE Stop s SET s.position = s.position - 1 " +
        "WHERE s.line.id = :lineId AND s.position > :pos")
    void shiftPositionsBackward(@Param("lineId") Long lineId, @Param("pos") Integer pos);

    // 1. Sposta le fermate in negativo (es: 2 -> -2, 3 -> -3) per liberare i numeri positivi
    @Modifying
    @Transactional
    @Query("UPDATE Stop s SET s.position = s.position * -1 " +
           "WHERE s.line.id = :lineId AND s.position >= :pos")
    void shiftToNegative(@Param("lineId") Long lineId, @Param("pos") Integer pos);

    // 2. Riporta i negativi in positivo incrementati di 1 (es: -2 -> 3, -3 -> 4)
    @Modifying
    @Transactional
    @Query("UPDATE Stop s SET s.position = ABS(s.position) + 1 " +
           "WHERE s.line.id = :lineId AND s.position < 0")
    void shiftFromNegativeToPositive(@Param("lineId") Long lineId);
}
