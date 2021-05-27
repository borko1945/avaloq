package com.borislav.diceroller.diceroller;

import com.borislav.diceroller.diceroller.dto.DiceRollRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class DiceRollerService {
    List<DiceRollStatistics> roll(DiceRollRequest request) {
        Map<Integer, Integer> statistics = new HashMap<>();

        for(int rollNum = 0; rollNum < request.getRolls(); rollNum++){
            int rollResult = 0;
            for (int i = 0; i < request.getDices(); i++) {
                rollResult += rollDice(request.getDiceSides());
            }

            statistics.merge(rollResult, 1, Integer::sum);
        }

        return toDiceRollResult(statistics);
    }

    private List<DiceRollStatistics> toDiceRollResult(Map<Integer, Integer> statistics) {
        return statistics.entrySet().stream()
                .map(entry -> new DiceRollStatistics(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private int rollDice(int diceSides) {
        Random ran = new Random();
        return ran.nextInt(diceSides) + 1;
    }
}
