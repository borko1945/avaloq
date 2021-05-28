package com.borislav.diceroller.simulation.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class DiceRollResponseDto {
    private final int dices;
    private final int rolls;
    private final int diceSides;
    private final List<DiceRollStatisticsDto> result;
}
