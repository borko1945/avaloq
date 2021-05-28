package com.borislav.diceroller.simulation;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class SimulationDiceRollGenerator {
    private final Random ran = new Random();

    int rollDice(int diceSides) {
        return ran.nextInt(diceSides) + 1;
    }
}
