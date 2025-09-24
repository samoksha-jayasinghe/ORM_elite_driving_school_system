package com.example.orm_elite_driving_school_system.bo.custom;

import com.example.orm_elite_driving_school_system.bo.SuperBO;
import com.example.orm_elite_driving_school_system.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUsers() throws Exception;

    String getLastUserId() throws Exception;

    boolean saveUsers(UserDTO t) throws Exception;

    boolean updateUsers(UserDTO t) throws Exception;

    boolean deleteUsers(String id) throws Exception;

    List<String> getAllUserIds() throws Exception;

    Optional<UserDTO> findByUserId(String id) throws Exception;

    String generateNextUserId();

    UserDTO getUserByUsername(String inputUsername);
}
