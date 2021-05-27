package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulationquery.dto.SimulationQueryListResponseDto;
import com.borislav.diceroller.simulationquery.dto.SimulationsPerDiceAndDiceSidesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationQueryService {
    private final SimulationQueryRepository repository;

    @Transactional(readOnly = true)
    SimulationQueryListResponseDto<SimulationsPerDiceAndDiceSidesDto> getTotalSimulationsGroupedByDiceAndDiceNumber() {
        return new SimulationQueryListResponseDto(repository.findAllGroupByDicesAndDiceSides());
    }
}
