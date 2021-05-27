package com.borislav.diceroller.simulation;

import com.borislav.diceroller.simulation.model.DiceRollSimulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SimulationRepository extends CrudRepository<DiceRollSimulation, Long> {
}
