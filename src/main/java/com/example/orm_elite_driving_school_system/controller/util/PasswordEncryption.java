package com.example.orm_elite_driving_school_system.controller.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }
}
