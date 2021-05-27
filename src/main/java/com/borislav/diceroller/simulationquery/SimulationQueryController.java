package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulationquery.dto.SimulationQueryListResponseDto;
import com.borislav.diceroller.simulationquery.dto.SimulationsPerDiceAndDiceSidesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class SimulationQueryController {

    private final SimulationQueryService simulationQueryService;

    @GetMapping("/simulations")
    public SimulationQueryListResponseDto<SimulationsPerDiceAndDiceSidesDto> getByDiceAndDiceSides() {
        return simulationQueryService.getTotalSimulationsGroupedByDiceAndDiceNumber();
    }

//    @GetMapping("simulations")
//    public void getByDiceAndDiceSides(@RequestParam int dices, @RequestParam int diceSides) {
//
//    }
}
