package com.borislav.diceroller.diceroller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DiceRollStatistics {
    @JsonProperty
    private final int rollResult;

    @JsonProperty
    private final int totalRolls;
}
