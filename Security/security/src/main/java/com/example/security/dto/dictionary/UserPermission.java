package com.example.security.dto.dictionary;

public enum UserPermission {

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
