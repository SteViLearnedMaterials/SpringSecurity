package com.example.security.dto.dictionary;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.security.dto.dictionary.UserPermission.COURSE_READ;
import static com.example.security.dto.dictionary.UserPermission.COURSE_WRITE;
import static com.example.security.dto.dictionary.UserPermission.USER_READ;
import static com.example.security.dto.dictionary.UserPermission.USER_WRITE;


public enum Role {

    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, USER_READ, USER_WRITE)),
    USER(Sets.newHashSet());

    private final Set<UserPermission> permissions;

    Role(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
