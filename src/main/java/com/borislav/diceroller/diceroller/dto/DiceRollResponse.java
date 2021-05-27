package com.borislav.diceroller.diceroller.dto;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class DiceRollResponse {
    private final List<DiceRollStatistics> list;
}
