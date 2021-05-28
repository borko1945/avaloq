package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import com.borislav.diceroller.simulation.model.SimulationResult;
import com.borislav.diceroller.simulationquery.dto.SimulationDiceSumDistributionDto;
import com.borislav.diceroller.simulationquery.dto.SimulationQueryListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimulationQueryService {
    private final SimulationQueryRepository repository;

    @Transactional(readOnly = true)
    SimulationQueryListResponseDto getTotalSimulationsGroupedByDiceAndDiceNumber() {
        return new SimulationQueryListResponseDto(repository.findAllGroupByDicesAndDiceSides());
    }

    @Transactional(readOnly = true)
    public SimulationQueryListResponseDto getDistributionByDicesAndDiceSides(int dices, int diceSides) {
        List<DiceRollSimulation> simulations = repository.findByDicesAndDiceSides(dices, diceSides);

        long totalRolls = getTotalRolls(simulations);
        Map<Integer, Integer> distribution = getTotalRollsPerDiceSum(simulations);

        return toDistributionResultDto(distribution, totalRolls);
    }

    private SimulationQueryListResponseDto toDistributionResultDto(Map<Integer, Integer> distribution, long totalRolls) {
        return new SimulationQueryListResponseDto(distribution.entrySet().stream()
                .map(entry -> toSimulationDiceSumDistributionDto(totalRolls, entry))
                .collect(Collectors.toList()));
    }

    private SimulationDiceSumDistributionDto toSimulationDiceSumDistributionDto(long totalRolls, Map.Entry<Integer, Integer> entry) {
        return new SimulationDiceSumDistributionDto(entry.getKey(), getDistributionPercentage(entry.getValue(), totalRolls));
    }

    private double getDistributionPercentage(long rolls, long totalRolls) {
        return roundDouble((rolls / (double)totalRolls) * 100);
    }

    private double roundDouble(double value) {
        return BigDecimal.valueOf(value).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    private Map<Integer, Integer> getTotalRollsPerDiceSum(List<DiceRollSimulation> simulations) {
        Map<Integer, Integer> distribution = simulations.stream()
                .flatMap(diceRollSimulation -> diceRollSimulation.getResults().stream())
                .collect(Collectors.toMap(SimulationResult::getDicesSum, SimulationResult::getTotalRolls, Integer::sum));
        return distribution;
    }

    private long getTotalRolls(List<DiceRollSimulation> simulations) {
        return simulations.stream().mapToLong(DiceRollSimulation::getRolls).sum();
    }
}
