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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/teams")
// @PreAuthorize("hasRole('ORGANIZER')")
public class TeamManagementController {

    private final TeamService teamService;
    private final StepCounterService stepCounterService;

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO request) {
        Optional<TeamDTO> teamOptional = teamService.createTeamWithEmployees(request);
        if (teamOptional.isPresent()) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teamOptional.get().id())
                .toUri();
        return ResponseEntity.created(location).body(teamOptional.get());
    }
        throw new UnsupportedOperationException("Some errors occurred, cannot create a step counter");
    }

    /**
     * US 1: Organizer can create a Team Step Counter
     * @param request
     * @return
     */
    @PostMapping("/addTeamStepCounter")
    public ResponseEntity<StepCounterDTO> createTeamStepCounter(@Valid @RequestBody StepCounterDTO request) {
        Optional<StepCounterDTO> createdStepCounterOptional = stepCounterService.addTeamStepCounter(request);
        if (createdStepCounterOptional.isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdStepCounterOptional.get().id())
                    .toUri();
            return ResponseEntity.created(location).body(createdStepCounterOptional.get());
        }
        throw new UnsupportedOperationException("Some errors occurred, cannot create a step counter");
    }

    /**
     * US 1: Organizer can remove a Team step counter
     * @param teamId
     * @return
     */
    @PutMapping("/removeTeamStepCounter/{teamId}")
    public ResponseEntity<TeamDTO> removeTeamStepCounterId(@PathVariable Long teamId) {
        Optional<TeamDTO> team = teamService.removeStepCounter(teamId);
        if (team.isPresent()) {
            return ResponseEntity.ok(team.get());
        }
        throw new UnsupportedOperationException("Some errors occurred, cannot remove the step counter");
    }

    /**
     * US 4: As the organizer I want to get a list of all team scores in descending order
     * @return
     */
    @GetMapping("/leaderboard")
    public ResponseEntity<List<StepCounterDTO>> getLeaderboard() {
        Optional<List<StepCounterDTO>> stepCountersOptional = stepCounterService.getAllTeamScoreDesc();
        if (stepCountersOptional.isPresent()) {
            return ResponseEntity.ok(stepCountersOptional.get());
        }
        throw new UnsupportedOperationException("The team score data is not available");
    }

    @GetMapping(value = "/leaderboardFlux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<StepCounterDTO>> getDevices() {
        Optional<List<StepCounterDTO>> stepCountersOptional = stepCounterService.getAllTeamScoreDesc();
        if(stepCountersOptional.isPresent()) {
            return ResponseEntity.ok(Flux.<StepCounterDTO>fromIterable(stepCountersOptional.get()));
        }
        throw new UnsupportedOperationException("The team score data is not available");
    }

}
