package com.hugecorp.teamwalk.controller;

import com.hugecorp.teamwalk.domain.Employee;
import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.mapper.StepCounterMapper;
import com.hugecorp.teamwalk.mapper.TeamMapper;
import com.hugecorp.teamwalk.model.StepCounterDTO;
import com.hugecorp.teamwalk.service.EmployeeService;
import com.hugecorp.teamwalk.service.StepCounterService;
import com.hugecorp.teamwalk.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final EmployeeService employeeService;
    private final StepCounterService stepCounterService;
    private final StepCounterMapper stepCounterMapper;

    /**
     * US 2: As a team member I want to add steps to my team step counter So that I can help my team win
     * @param employeeId
     * @param steps
     * @return
     */
    @PutMapping("/addSteps/{employeeId}")
    public ResponseEntity<StepCounterDTO> addStepsToTeam(@PathVariable Long employeeId, @RequestParam(value = "steps", required = true) String steps) {

        Optional<Team> teamOptional = employeeService.addStepsToTeamStepCounterByEmployeeId(employeeId, Integer.valueOf(steps) );
        if (teamOptional.isPresent() && null != teamOptional.get().getStepcounter()) {
            StepCounter stepCounter = stepCounterService.findStepCounterById(teamOptional.get().getStepcounter().getId()).orElse(null);
            if (stepCounter != null) {
                return ResponseEntity.ok(stepCounterMapper.toStepCounterDto(stepCounter));
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * US 3: As a team member I want to get my team’s step count at
     * @param employeeId
     * @return
     */
    @GetMapping("/teamscore/{employeeId}")
    public ResponseEntity<StepCounterDTO> retrieveTeamSteps(@PathVariable Long employeeId) {

        Optional<Employee> employeeOptional = employeeService.findEmployeeById(employeeId);
        if (employeeOptional.isPresent()) {
            StepCounter stepCounter = stepCounterService.findStepCounterById(employeeOptional.get().getTeam().getStepcounter().getId()).orElse(null);
            if (stepCounter != null) {
                return ResponseEntity.ok(stepCounterMapper.toStepCounterDto(stepCounter));
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
