package com.roerdev.pruebaapiassert.services;

import com.roerdev.pruebaapiassert.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    CompletableFuture<UserDto> getUser(UUID id) throws Exception;
    CompletableFuture<Page<UserDto>> getAllUser(Pageable paginador) throws Exception;
    CompletableFuture<UserDto> saveUser(UserDto user) throws Exception;
    CompletableFuture<UserDto> updateUser(UUID id, UserDto user) throws Exception;
    CompletableFuture<UUID> deleteUser(UUID id) throws Exception;

}
