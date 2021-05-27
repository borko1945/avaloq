package com.borislav.diceroller.simulation.dto;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class DiceRollResponseDto {
    private final List<DiceRollStatisticsDto> list;
}
