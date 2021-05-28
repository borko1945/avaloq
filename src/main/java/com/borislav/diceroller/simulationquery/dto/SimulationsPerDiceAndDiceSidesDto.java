package com.borislav.diceroller.simulationquery.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SimulationsPerDiceAndDiceSidesDto {
    private final int dices;
    private final int diceSides;
    private final long totalSimulations;
    private final long totalRolls;

    public SimulationsPerDiceAndDiceSidesDto(int dices, int diceSides, long totalSimulations, long totalRolls) {
        this.dices = dices;
        this.diceSides = diceSides;
        this.totalSimulations = totalSimulations;
        this.totalRolls = totalRolls;
    }
}
