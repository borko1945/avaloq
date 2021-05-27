package com.borislav.diceroller.diceroller;

import com.borislav.diceroller.diceroller.dto.DiceRollRequest;
import com.borislav.diceroller.diceroller.dto.DiceRollResponse;
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
public class DiceRollerController {
    private final DiceRollerService diceRollerService;

    @PostMapping("/dice-roller")
    public DiceRollResponse createDiceRoll(@RequestParam(defaultValue = "3") @Min(1) int dices,
                                           @RequestParam(defaultValue = "100") @Min(1) int rolls,
                                           @RequestParam(defaultValue = "6") @Min(4) int diceSides) {
        DiceRollRequest request = new DiceRollRequest(dices, rolls, diceSides);
        return diceRollerService.roll(request);
    }
}
