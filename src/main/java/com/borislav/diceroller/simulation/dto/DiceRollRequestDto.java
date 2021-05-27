package com.borislav.diceroller.simulation.dto;

import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import lombok.RequiredArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;

@RequiredArgsConstructor
@Getter
public class DiceRollRequestDto {
    @Min(1)
    private final int dices;
    @Min(1)
    private final int rolls;
    @Min(4)
    private final int diceSides;

    public DiceRollSimulation toDiceRollSimulationModel() {
        return DiceRollSimulation.builder()
                .dices(dices)
                .rolls(rolls)
                .diceSides(diceSides).build();
    }
}
