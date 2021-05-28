package com.borislav.diceroller.simulation;

import com.borislav.diceroller.simulation.dto.DiceRollRequestDto;
import com.borislav.diceroller.simulation.dto.DiceRollResponseDto;
import com.borislav.diceroller.simulation.dto.DiceRollStatisticsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SimulationController.class)
class SimulationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SimulationService simulationService;

    @Test
    public void createDiceRoll_noQueryParamsUsed_defaultAreUsed() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/simulations"))
                .andExpect(status().isOk());

        verify(simulationService, times(1)).roll(new DiceRollRequestDto(3, 100, 6));
    }

    @Test
    public void createDiceRoll_queryParamsProvided_providedParamsUsedAndCorrectResponseReturned() throws Exception {
        when(simulationService.roll(new DiceRollRequestDto(2, 10, 8))).thenReturn(new DiceRollResponseDto(
                2,
                10,
                8,
                List.of(new DiceRollStatisticsDto(
                        5,
                        10
                ))
        ));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/simulations?dices=2&rolls=10&diceSides=8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dices").value("2"))
                .andExpect(jsonPath("$.rolls").value("10"))
                .andExpect(jsonPath("$.diceSides").value("8"))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result",hasSize(1)))
                .andExpect(jsonPath("$.result[0].dicesSum").value(5))
                .andExpect(jsonPath("$.result[0].totalRolls").value(10));

        verify(simulationService, times(1)).roll(new DiceRollRequestDto(2, 10, 8));
    }

    @Test
    public void createDiceRoll_dicesQueryParamInvalid_badRequestReturned() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/simulations?dices=0"))
                .andExpect(status().isBadRequest());

        verify(simulationService, never()).roll(any());
    }

    @Test
    public void createDiceRoll_rollsQueryParamInvalid_badRequestReturned() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/simulations?rolls=0"))
                .andExpect(status().isBadRequest());

        verify(simulationService, never()).roll(any());
    }

    @Test
    public void createDiceRoll_diceSidesQueryParamInvalid_badRequestReturned() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/simulations?diceSides=3"))
                .andExpect(status().isBadRequest());

        verify(simulationService, never()).roll(any());
    }
}