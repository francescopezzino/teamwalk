package com.hugecorp.teamwalk.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record TeamDTO(@Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id, String name, List<EmployeeDTO> employees) {
}
