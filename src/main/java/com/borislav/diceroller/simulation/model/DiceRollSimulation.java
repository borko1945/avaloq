package com.borislav.diceroller.simulation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiceRollSimulation {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
