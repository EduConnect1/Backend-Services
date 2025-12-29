package com.example.demo.auth.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RoleEnum {
    @JsonProperty("ROLE_USER")
    ROLE_USER,
    @JsonProperty("ROLE_ADMIN")
    ROLE_ADMIN,
    @JsonProperty("ROLE_STUDENT")
    ROLE_STUDENT,
    @JsonProperty("ROLE_TEACHER")
    ROLE_TEACHER;
}
