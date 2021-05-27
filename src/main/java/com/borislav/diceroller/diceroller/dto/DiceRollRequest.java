package com.borislav.diceroller.diceroller.dto;

import com.borislav.diceroller.diceroller.model.DiceRollSimulation;
import lombok.RequiredArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;

@RequiredArgsConstructor
@Getter
public class DiceRollRequest {
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
