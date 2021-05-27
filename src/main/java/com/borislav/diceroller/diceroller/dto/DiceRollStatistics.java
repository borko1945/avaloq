package com.borislav.diceroller.diceroller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DiceRollStatistics {
    @JsonProperty
    private final int dicesSum;

    @JsonProperty
    private final int totalRolls;
}
