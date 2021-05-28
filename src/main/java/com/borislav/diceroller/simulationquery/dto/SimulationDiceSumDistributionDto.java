package com.borislav.diceroller.simulationquery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SimulationDiceSumDistributionDto {
    @JsonProperty
    private final int dicesSum;

    @JsonProperty
    private final double distributionPercentage;
}
