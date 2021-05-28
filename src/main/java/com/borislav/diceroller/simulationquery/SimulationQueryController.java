package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulationquery.dto.SimulationDistributionResponseDto;
import com.borislav.diceroller.simulationquery.dto.SimulationPerDiceAndDiceSidesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class SimulationQueryController {

    private final SimulationQueryService simulationQueryService;

    @GetMapping("/simulations")
    public SimulationPerDiceAndDiceSidesResponseDto getByDiceAndDiceSides() {
        return simulationQueryService.getTotalSimulationsGroupedByDiceAndDiceNumber();
    }

    @GetMapping("/simulations/distribution")
    public SimulationDistributionResponseDto getByDiceAndDiceSides(@RequestParam int dices, @RequestParam int diceSides) {
        return simulationQueryService.getDistributionByDicesAndDiceSides(dices, diceSides);
    }
}
