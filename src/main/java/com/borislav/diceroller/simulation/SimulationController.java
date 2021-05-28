package com.borislav.diceroller.simulation;

import com.borislav.diceroller.simulation.dto.DiceRollRequestDto;
import com.borislav.diceroller.simulation.dto.DiceRollResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RequestMapping("/api")
@RestController
@Validated
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationService simulationService;

    @PostMapping("/simulations")
    public DiceRollResponseDto createDiceRoll(@RequestParam(defaultValue = "3") @Min(1) int dices,
                                              @RequestParam(defaultValue = "100") @Min(1) int rolls,
                                              @RequestParam(defaultValue = "6") @Min(4) int diceSides) {
        DiceRollRequestDto request = new DiceRollRequestDto(dices, rolls, diceSides);
        return simulationService.roll(request);
    }
}
