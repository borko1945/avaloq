package com.borislav.diceroller.simulationquery.dto;

import lombok.Getter;

@Getter
public class SimulationsPerDiceAndDiceSidesDto {
    private final int dices;
    private final int diceSides;
    private final long dicesSum;
    private final long totalRolls;

    public SimulationsPerDiceAndDiceSidesDto(int dices, int diceSides, long dicesSum, long totalRolls) {
        this.dices = dices;
        this.diceSides = diceSides;
        this.dicesSum = dicesSum;
        this.totalRolls = totalRolls;
    }
}
