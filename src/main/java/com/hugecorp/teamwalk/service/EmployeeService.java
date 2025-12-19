package com.hugecorp.teamwalk.service;

import com.hugecorp.teamwalk.domain.Employee;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.model.TeamDTO;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findEmployeeById(Long id);

    Optional<TeamDTO> addStepsToTeamStepCounterByEmployeeId(Long employeeId, Integer employeeSteps);

}
