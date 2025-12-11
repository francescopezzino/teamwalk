package com.hugecorp.teamwalk.model;

import java.util.List;

public record TeamDTO(Long id, String name, List<EmployeeDTO> employees) {
}
