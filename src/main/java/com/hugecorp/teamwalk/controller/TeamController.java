package com.hugecorp.teamwalk.controller;

import com.hugecorp.teamwalk.domain.Employee;
import com.hugecorp.teamwalk.exception.ResourceNotFoundException;
import com.hugecorp.teamwalk.model.StepCounterDTO;
import com.hugecorp.teamwalk.model.TeamDTO;
import com.hugecorp.teamwalk.service.EmployeeService;
import com.hugecorp.teamwalk.service.StepCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final EmployeeService employeeService;
    private final StepCounterService stepCounterService;

    /**
     * US 2: As a team member I want to add steps to my team step counter So that I can help my team win
     * @param employeeId
     * @param steps
     * @return
     */
    @PutMapping("/addSteps/{employeeId}")
    public ResponseEntity<StepCounterDTO> addStepsToTeam(@PathVariable Long employeeId, @RequestParam(required = true) String steps) {

        TeamDTO teamDTO = employeeService.addStepsToTeamStepCounterByEmployeeId(employeeId, Integer.valueOf(steps))
                .orElseThrow(() -> new ResourceNotFoundException("Employee or Team not found: " + employeeId));
        StepCounterDTO dto = stepCounterService.getStepCounterById(teamDTO.id())
                .orElseThrow(() -> new ResourceNotFoundException("Step counter not initialized for team"));
        return ResponseEntity.ok(dto);
    }

    /**
     * US 3: As a team member I want to get my team’s step count at
     * @param employeeId
     * @return
     */
    @GetMapping("/teamscore/{employeeId}")
    public ResponseEntity<StepCounterDTO> retrieveTeamSteps(@PathVariable Long employeeId) {

        Employee employee = employeeService.findEmployeeById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee or Team not found: " + employeeId));
        StepCounterDTO stepCounter = stepCounterService.getStepCounterById(employee.getTeam().getStepcounter().getId()).orElseThrow(() -> new ResourceNotFoundException("Step Counter not found: " + employeeId));
        return ResponseEntity.ok(stepCounter);
    }


}

