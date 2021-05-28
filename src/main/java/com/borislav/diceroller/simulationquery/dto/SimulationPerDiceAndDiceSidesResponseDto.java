package com.borislav.diceroller.simulationquery.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class SimulationPerDiceAndDiceSidesResponseDto {
    private final List<SimulationsPerDiceAndDiceSidesDto> result;
}
