package com.borislav.diceroller.diceroller;

import com.borislav.diceroller.diceroller.dto.DiceRollRequest;
import com.borislav.diceroller.diceroller.dto.DiceRollResponse;
import com.borislav.diceroller.diceroller.dto.DiceRollStatistics;
import com.borislav.diceroller.diceroller.model.DiceRollSimulation;
import com.borislav.diceroller.diceroller.model.SimulationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiceRollerService {

    private final DiceRollSimulationRepository repository;

    @Transactional
    public DiceRollResponse roll(DiceRollRequest request) {
        Map<Integer, Integer> statistics = performSimulation(request);

        storeSimulationResults(request, statistics);

        return toDiceRollResponse(statistics);
    }

    private Map<Integer, Integer> performSimulation(DiceRollRequest request) {
        Map<Integer, Integer> statistics = new HashMap<>();

        for(int rollNum = 0; rollNum < request.getRolls(); rollNum++){
            int rollResult = 0;
            for (int i = 0; i < request.getDices(); i++) {
                rollResult += rollDice(request.getDiceSides());
            }

            statistics.merge(rollResult, 1, Integer::sum);
        }
        return statistics;
    }

    private void storeSimulationResults(DiceRollRequest request, Map<Integer, Integer> statistics) {
        DiceRollSimulation simulation = request.toDiceRollSimulationModel();
        statistics.forEach((dicesSum, totalRolls) -> simulation.addResult(SimulationResult.builder()
                .dicesSum(dicesSum)
                .totalRolls(totalRolls)
                .build()));

        repository.save(simulation);
    }

    private DiceRollResponse toDiceRollResponse(Map<Integer, Integer> statistics) {
        return new DiceRollResponse(statistics.entrySet().stream()
                .map(entry -> new DiceRollStatistics(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
    }

    private int rollDice(int diceSides) {
        Random ran = new Random();
        return ran.nextInt(diceSides) + 1;
    }
}
