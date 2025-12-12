package com.hugecorp.teamwalk.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmployeeDTO(@Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id, String firstName, String lastName) {
}
