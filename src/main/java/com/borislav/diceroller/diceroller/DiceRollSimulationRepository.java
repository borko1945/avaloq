package com.borislav.diceroller.diceroller;

import com.borislav.diceroller.diceroller.model.DiceRollSimulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DiceRollSimulationRepository extends CrudRepository<DiceRollSimulation, Long> {
}
