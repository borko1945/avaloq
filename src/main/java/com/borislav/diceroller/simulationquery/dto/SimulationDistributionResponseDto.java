package com.borislav.diceroller.simulationquery.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class SimulationDistributionResponseDto {
    private final int dices;
    private final int diceSides;
    private final List<SimulationDiceSumDistributionDto> distribution;
}
