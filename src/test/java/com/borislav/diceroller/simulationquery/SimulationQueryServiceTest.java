package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import com.borislav.diceroller.simulation.model.SimulationResult;
import com.borislav.diceroller.simulationquery.dto.SimulationDiceSumDistributionDto;
import com.borislav.diceroller.simulationquery.dto.SimulationDistributionResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimulationQueryServiceTest {
    @Mock
    private SimulationQueryRepository repository;

    @InjectMocks
    private SimulationQueryService service;

    @Test
    public void getDistributionByDicesAndDiceSides_happyPath_returnsCorrectResult() {
        when(repository.findByDicesAndDiceSides(3, 6)).thenReturn(List.of(
                DiceRollSimulation.builder()
                        .dices(3)
                        .diceSides(6)
                        .rolls(100)
                        .results(List.of(
                                SimulationResult.builder()
                                        .dicesSum(3)
                                        .totalRolls(1)
                                        .build(),
                                SimulationResult.builder()
                                        .dicesSum(4)
                                        .totalRolls(2)
                                        .build(),
                                SimulationResult.builder()
                                        .dicesSum(5)
                                        .totalRolls(6)
                                        .build()))
                        .build(),
                DiceRollSimulation.builder()
                        .dices(3)
                        .diceSides(6)
                        .rolls(200)
                        .results(List.of(
                                SimulationResult.builder()
                                        .dicesSum(3)
                                        .totalRolls(3)
                                        .build(),
                                SimulationResult.builder()
                                        .dicesSum(4)
                                        .totalRolls(1)
                                        .build(),
                                SimulationResult.builder()
                                        .dicesSum(5)
                                        .totalRolls(5)
                                        .build()))
                        .build()
        ));

        SimulationDistributionResponseDto actual = service.getDistributionByDicesAndDiceSides(3, 6);

        SimulationDistributionResponseDto expected = new SimulationDistributionResponseDto(
                3,
                6,
                List.of(new SimulationDiceSumDistributionDto(
                                3,
                                1.33
                        ),
                        new SimulationDiceSumDistributionDto(
                                4,
                                1.0
                        ),
                        new SimulationDiceSumDistributionDto(
                                5,
                                3.66
                        ))
        );

        assertThat(expected, equalTo(actual));
    }
}