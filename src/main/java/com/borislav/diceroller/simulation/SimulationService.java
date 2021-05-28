package com.borislav.diceroller.simulation;

import com.borislav.diceroller.simulation.dto.DiceRollRequestDto;
import com.borislav.diceroller.simulation.dto.DiceRollResponseDto;
import com.borislav.diceroller.simulation.dto.DiceRollStatisticsDto;
import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import com.borislav.diceroller.simulation.model.SimulationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
class SimulationService {

    private final SimulationRepository repository;
    private final SimulationDiceRollGenerator generator;

    @Transactional
    public DiceRollResponseDto roll(DiceRollRequestDto request) {
        Map<Integer, Integer> statistics = performSimulation(request);

        storeSimulationResults(request, statistics);

        return toDiceRollResponse(request, statistics);
    }

    private Map<Integer, Integer> performSimulation(DiceRollRequestDto request) {
        Map<Integer, Integer> statistics = new HashMap<>();

        for(int rollNum = 0; rollNum < request.getRolls(); rollNum++){
            int rollResult = 0;
            for (int i = 0; i < request.getDices(); i++) {
                rollResult += generator.rollDice(request.getDiceSides());
            }

            statistics.merge(rollResult, 1, Integer::sum);
        }
        return statistics;
    }

    private void storeSimulationResults(DiceRollRequestDto request, Map<Integer, Integer> statistics) {
        DiceRollSimulation simulation = request.toDiceRollSimulationModel();
        statistics.forEach((dicesSum, totalRolls) -> simulation.addResult(SimulationResult.builder()
                .dicesSum(dicesSum)
                .totalRolls(totalRolls)
                .build()));

        repository.save(simulation);
    }

    private DiceRollResponseDto toDiceRollResponse(DiceRollRequestDto request, Map<Integer, Integer> statistics) {
        return new DiceRollResponseDto(
                request.getDices(),
                request.getRolls(),
                request.getDiceSides(),
                statistics.entrySet().stream()
                .map(entry -> new DiceRollStatisticsDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
    }
}
