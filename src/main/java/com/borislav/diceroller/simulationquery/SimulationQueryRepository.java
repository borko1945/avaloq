package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import com.borislav.diceroller.simulationquery.dto.SimulationsPerDiceAndDiceSidesDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SimulationQueryRepository extends CrudRepository<DiceRollSimulation, Long> {
    List<DiceRollSimulation> findByDicesAndDiceSides(int dices, int diceSides);

    @Query("SELECT new com.borislav.diceroller.simulationquery.dto.SimulationsPerDiceAndDiceSidesDto(" +
            "s.dices, s.diceSides, COUNT(s), SUM(s.rolls)) " +
    "FROM DiceRollSimulation s GROUP BY s.dices, s.diceSides")
    List<SimulationsPerDiceAndDiceSidesDto> findAllGroupByDicesAndDiceSides();
}
