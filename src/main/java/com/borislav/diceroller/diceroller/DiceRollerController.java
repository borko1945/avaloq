package com.borislav.diceroller.diceroller;

import com.borislav.diceroller.diceroller.dto.DiceRollRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping("/api")
@RestController
@Validated
public class DiceRollerController {

    private final DiceRollerService diceRollerService;

    public DiceRollerController(DiceRollerService diceRollerService) {
        this.diceRollerService = diceRollerService;
    }

    @PostMapping("/dice-roller")
    public List<DiceRollStatistics> createDiceRoll(@RequestParam(defaultValue = "3") @Min(1) int dices,
                                                   @RequestParam(defaultValue = "100") @Min(1) int rolls,
                                                   @RequestParam(defaultValue = "6") @Min(4) int diceSides) {
        DiceRollRequest request = new DiceRollRequest(dices, rolls, diceSides);
        return diceRollerService.roll(request);
    }
}
