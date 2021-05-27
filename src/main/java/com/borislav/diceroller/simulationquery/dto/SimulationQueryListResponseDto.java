package com.borislav.diceroller.simulationquery.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class SimulationQueryListResponseDto<T> {
    private final List<T> list;
}
