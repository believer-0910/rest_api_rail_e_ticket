package com.example.rail_e_ticket_api.util.enums;

import lombok.Getter;

@Getter
public enum RolePermissionEnum {
    ROLE_ADMIN(1),
    ROLE_MODERATOR(2),
    ROLE_USER(4),


    ADD_DESTINATION(16),
    GET_ALL_DESTINATION(32),
    GET_DESTINATION(64),
    EDIT_DESTINATION(128),
    DELETE_DESTINATION(128),

    ADD_STATION(256),
    GET_ALL_STATION(512),
    GET_STATION(1028),
    EDIT_STATION(2048),
    DELETE_STATION(4096)



    ;



    private int code;

    RolePermissionEnum(int code) {
        this.code = code;
    }
}
