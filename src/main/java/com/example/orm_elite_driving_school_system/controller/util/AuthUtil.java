package com.example.orm_elite_driving_school_system.controller.util;

import com.example.orm_elite_driving_school_system.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

public class AuthUtil {
    @Getter
    @Setter
    private static UserDTO currentUser;

    public static String getRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    public static void clear() {
        currentUser = null;
    }
}
