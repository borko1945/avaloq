package com.borislav.diceroller.simulation;

import com.borislav.diceroller.simulation.dto.DiceRollRequestDto;
import com.borislav.diceroller.simulation.dto.DiceRollResponseDto;
import com.borislav.diceroller.simulation.dto.DiceRollStatisticsDto;
import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import com.borislav.diceroller.simulation.model.SimulationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimulationServiceTest {

    @Mock
    private SimulationDiceRollGenerator generator;

    @Mock
    private SimulationRepository repository;

    @Captor
    ArgumentCaptor<DiceRollSimulation> simulationEntityCaptor;

    @InjectMocks
    private SimulationService testMe;

    @Test
    void roll_invokedWith_3Dices_10Rolls_30DiceRollsAreGenerated() {
        DiceRollRequestDto request = new DiceRollRequestDto(3, 10, 5);
        when(generator.rollDice(5)).thenReturn(5);

        testMe.roll(request);

        verify(generator, times(30)).rollDice(5);
    }

    @Test
    void roll_invokedWith_3Dices_3Rolls_6Sides_expectedEntityStoredInDbAndDtoReturned() {
        DiceRollRequestDto request = new DiceRollRequestDto(3, 3, 6);
        when(generator.rollDice(6)).thenReturn(3,3,3,1,2,6,5,6,1);

        DiceRollResponseDto actualDto = testMe.roll(request);

        verify(repository, times(1)).save(simulationEntityCaptor.capture());

        verifyDbEntity();
        verifyDto(request, actualDto);
    }

    private void verifyDto(DiceRollRequestDto request, DiceRollResponseDto actual) {
        DiceRollResponseDto expected = new DiceRollResponseDto(
                request.getDices(),
                request.getRolls(),
                request.getDiceSides(),
                List.of(new DiceRollStatisticsDto(9, 2),
                        new DiceRollStatisticsDto(12,1))
        );

        assertEquals(expected, actual);
    }

    private void verifyDbEntity() {
        DiceRollSimulation expectedDb = DiceRollSimulation.builder()
                .dices(3)
                .diceSides(6)
                .rolls(3)
                .results(List.of(SimulationResult.builder()
                                .dicesSum(9)
                                .totalRolls(2)
                                .build(),
                                SimulationResult.builder()
                                .dicesSum(12)
                                .totalRolls(1)
                                .build()))
                .build();

        assertEquals(expectedDb, simulationEntityCaptor.getValue());
    }
}