package com.borislav.diceroller.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationDiceRollGeneratorTest {

    @Test
    void rollDice_invoked1000timesWith10DiceSides_expectedResultsInValidRange() {
        SimulationDiceRollGenerator generator = new SimulationDiceRollGenerator();

        for(int i = 0; i < 1000; i++) {
            int result = generator.rollDice(10);
            assertTrue(result > 0);
            assertTrue(result <= 10);
        }
    }
}