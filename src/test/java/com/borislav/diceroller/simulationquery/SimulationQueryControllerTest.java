package com.borislav.diceroller.simulationquery;

import com.borislav.diceroller.simulationquery.dto.SimulationDiceSumDistributionDto;
import com.borislav.diceroller.simulationquery.dto.SimulationDistributionResponseDto;
import com.borislav.diceroller.simulationquery.dto.SimulationPerDiceAndDiceSidesResponseDto;
import com.borislav.diceroller.simulationquery.dto.SimulationsPerDiceAndDiceSidesDto;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SimulationQueryController.class)
class SimulationQueryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SimulationQueryService simulationQueryService;

    @Test
    public void getSimulationsByDiceAndDiceSides_happyPathRequest_expectedResponseReturned() throws Exception {
        when(simulationQueryService.getTotalSimulationsGroupedByDiceAndDiceNumber()).thenReturn(
                new SimulationPerDiceAndDiceSidesResponseDto(
                        List.of(new SimulationsPerDiceAndDiceSidesDto(
                                3, 6, 18, 50
                        ))
                ));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/simulations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result",hasSize(1)))
                .andExpect(jsonPath("$.result[0].dices").value(3))
                .andExpect(jsonPath("$.result[0].diceSides").value(6))
                .andExpect(jsonPath("$.result[0].dicesSum").value(18))
                .andExpect(jsonPath("$.result[0].totalRolls").value(50));

        verify(simulationQueryService, times(1)).getTotalSimulationsGroupedByDiceAndDiceNumber();
    }

    @Test
    public void getByDiceAndDiceSides_happyPathRequest_expectedResponseReturned() throws Exception {
        when(simulationQueryService.getDistributionByDicesAndDiceSides(3,6)).thenReturn(
                new SimulationDistributionResponseDto(
                        3,
                        6,
                        List.of(new SimulationDiceSumDistributionDto(
                            10,
                            1.623
                        ))
                ));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/simulations/distribution?dices=3&diceSides=6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dices").value("3"))
                .andExpect(jsonPath("$.diceSides").value("6"))
                .andExpect(jsonPath("$.distribution").isArray())
                .andExpect(jsonPath("$.distribution",hasSize(1)))
                .andExpect(jsonPath("$.distribution[0].dicesSum").value(10))
                .andExpect(jsonPath("$.distribution[0].distributionPercentage").value("1.623"));

        verify(simulationQueryService, times(1)).getDistributionByDicesAndDiceSides(3,6);
    }

    @Test
    public void getByDiceAndDiceSides_queryParamsMissing_badRequestReturned() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/simulations/distribution"))
                .andExpect(status().isBadRequest());

        verify(simulationQueryService, never()).getDistributionByDicesAndDiceSides(anyInt(),anyInt());
    }
}