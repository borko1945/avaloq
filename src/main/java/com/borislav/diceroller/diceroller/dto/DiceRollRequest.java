package com.borislav.diceroller.diceroller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
public class DiceRollRequest {
    @Min(1)
    private final int dices;
    @Min(1)
    private final int rolls;
    @Min(4)
    private final int diceSides;
}
