package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import com.borislav.diceroller.simulationquery.dto.SimulationsPerDiceAndDiceSidesDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SimulationQueryRepositoryTest {

    @Autowired
    private SimulationQueryRepository repository;

    @Test
    public void findAllGroupByDicesAndDiceSides_happyPath_expectedResultsReturned() {
        repository.save(DiceRollSimulation.builder()
                .dices(3)
                .diceSides(6)
                .rolls(100)
                .build());
        repository.save(DiceRollSimulation.builder()
                .dices(3)
                .diceSides(6)
                .rolls(200)
                .build());
        repository.save(DiceRollSimulation.builder()
                .dices(1)
                .diceSides(4)
                .rolls(200)
                .build());

        List<SimulationsPerDiceAndDiceSidesDto> actual = repository.findAllGroupByDicesAndDiceSides();
        List<SimulationsPerDiceAndDiceSidesDto> expected = List.of(
                new SimulationsPerDiceAndDiceSidesDto(1, 4, 1, 200),
                new SimulationsPerDiceAndDiceSidesDto(3, 6, 2, 300));

        assertEquals(expected, actual);
    }

}