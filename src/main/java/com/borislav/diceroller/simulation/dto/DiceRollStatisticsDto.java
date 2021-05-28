package com.borislav.diceroller.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class DiceRollStatisticsDto {
    @JsonProperty
    private final int dicesSum;

    @JsonProperty
    private final int totalRolls;
}
