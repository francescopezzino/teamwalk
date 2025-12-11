package com.hugecorp.teamwalk.controller;

import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.mapper.StepCounterMapper;
import com.hugecorp.teamwalk.mapper.TeamMapper;
import com.hugecorp.teamwalk.model.StepCounterDTO;
import com.hugecorp.teamwalk.model.TeamDTO;
import com.hugecorp.teamwalk.repos.StepCounterRepository;
import com.hugecorp.teamwalk.service.StepCounterService;
import com.hugecorp.teamwalk.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/teams")
// @PreAuthorize("hasRole('ORGANIZER')")
public class TeamManagementController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final StepCounterService stepCounterService;
    private final StepCounterMapper stepCounterMapper;
    private final StepCounterRepository stepCounterRepository;

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO request) {
        Team createdTeam = teamService.createTeamWithEmployees(request);
        return new ResponseEntity<>(teamMapper.toTeamDto(createdTeam), HttpStatus.CREATED);
    }

    /**
     * US 1: Organizer can create a Team Step Counter
     * @param request
     * @return
     */
    @PostMapping("/addTeamStepCounter")
    public ResponseEntity<StepCounterDTO> createTeamStepCounter(@Valid @RequestBody StepCounterDTO request) {
        StepCounter createdStepCounter  = stepCounterService.addTeamStepCounter(request);
        return new ResponseEntity<>(stepCounterMapper.toStepCounterDto(createdStepCounter), HttpStatus.CREATED);
    }

    /**
     * US 1: Organizer can remove a Team step counter
     * @param teamId
     * @return
     */
    @PutMapping("/removeTeamStepCounter/{teamId}")
    public ResponseEntity<TeamDTO> removeTeamStepCounterId(@PathVariable Long teamId) {
        Optional<Team> team = teamService.removeStepCounter(teamId);
        if (team.isPresent()) {
            return ResponseEntity.ok(teamMapper.toTeamDto(team.get()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * US 4: As the organizer I want to get a list of all team scores in descending order
     * @return
     */
    @GetMapping("/leaderboard")
    public ResponseEntity<List<StepCounterDTO>> getLeaderboard() {
        List<StepCounter>  stepCounters = stepCounterRepository.findAllByOrderByStepsDesc();
        if (null != stepCounters && !stepCounters.isEmpty()) {
            return ResponseEntity.ok(stepCounterMapper.toStepCounterDtos(stepCounters));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/leaderboardFlux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<StepCounterDTO>> getDevices() {
        List<StepCounter>  stepCounters = stepCounterRepository.findAllByOrderByStepsDesc();
        if(null == stepCounters || stepCounters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<StepCounterDTO> stepCounterDTOS= stepCounterMapper.toStepCounterDtos(stepCounters);
        return ResponseEntity.ok(Flux.<StepCounterDTO>fromIterable(stepCounterDTOS));
    }

}
