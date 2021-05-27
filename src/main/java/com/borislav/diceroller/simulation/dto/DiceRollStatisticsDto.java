package com.borislav.diceroller.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DiceRollStatisticsDto {
    @JsonProperty
    private final int dicesSum;

    @JsonProperty
    private final int totalRolls;
}
