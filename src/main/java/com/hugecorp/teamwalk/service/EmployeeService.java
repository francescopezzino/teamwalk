package com.hugecorp.teamwalk.service;

import com.hugecorp.teamwalk.domain.Employee;
import com.hugecorp.teamwalk.domain.Team;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findEmployeeById(Long id);

    Optional<Team> findTeamByIdEmployeeId(Long employeeId);

    Optional<Team> addStepsToTeamStepCounterByEmployeeId(Long employeeId, Integer employeeSteps);

}
