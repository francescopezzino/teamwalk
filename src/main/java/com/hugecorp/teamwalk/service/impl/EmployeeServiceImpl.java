package com.hugecorp.teamwalk.service.impl;

import com.hugecorp.teamwalk.domain.Employee;
import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.repos.EmployeeRepository;
import com.hugecorp.teamwalk.repos.StepCounterRepository;
import com.hugecorp.teamwalk.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final StepCounterRepository stepCounterRepository;

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Team> addStepsToTeamStepCounterByEmployeeId(Long employeeId, Integer employeeSteps) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        Team team = null;
        Employee employee = null;
        if (employeeOptional.isPresent()) {
            employee = employeeOptional.get();
            team = employee.getTeam();
            if(null != team.getStepcounter()) {
                var id = team.getStepcounter().getId();

                Optional<StepCounter> stepCounterOptional = stepCounterRepository.findById(id);
                if (stepCounterOptional.isPresent()) {
                    StepCounter stepCounter = stepCounterOptional.get();
                    var steps = stepCounter.getSteps();
                    if (steps == null) {
                        steps = Integer.valueOf(0);
                    }
                    steps = stepCounter.getSteps() + employeeSteps;
                    stepCounter.setSteps(steps);
                    stepCounterRepository.save(stepCounter);
                }
            }
        }
        return Optional.ofNullable(team);
    }

}
