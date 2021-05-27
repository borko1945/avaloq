package com.borislav.diceroller.diceroller.model;

import lombok.Builder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "DiceRollSimulation")
@Table( name = "dice_roll_simulation",
        indexes = { @Index(name = "idx_dices_dicesides", columnList = "dices, dice_sides") })
@Builder
public class DiceRollSimulation {
    @GeneratedValue
    @Id
    private long id;

    @Column(name = "dices")
    private int dices;

    @Column(name = "rolls")
    private int rolls;

    @Column(name = "dice_sides")
    private int diceSides;

    @OneToMany(
            mappedBy = "simulation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<SimulationResult> results = new LinkedList<>();

    public void addResult(SimulationResult result) {
        results.add(result);
        result.setSimulation(this);
    }
}
