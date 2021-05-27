package com.borislav.diceroller.diceroller.model;

import lombok.Builder;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "SimulationResult")
@Table(name = "simulation_result")
@Builder
public class SimulationResult {
    @GeneratedValue
    @Id
    private long id;

    @Column(name = "dices_sum")
    private int dicesSum;

    @Column(name = "total_rolls")
    private int totalRolls;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private DiceRollSimulation simulation;
}
