package com.roerdev.pruebaapiassert.services.impl;

import com.roerdev.pruebaapiassert.exceptions.EntityNotFoundException;
import com.roerdev.pruebaapiassert.models.UserEntity;
import com.roerdev.pruebaapiassert.services.UserService;
import com.roerdev.pruebaapiassert.utils.Constants;
import com.roerdev.pruebaapiassert.dto.UserDto;
import com.roerdev.pruebaapiassert.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Async("threadPoolExecutor")
    public CompletableFuture<UserDto> getUser(UUID id) throws Exception {

        var user = this.getOneUser(id);
        return CompletableFuture.completedFuture(modelMapper.map(user, UserDto.class));
    }

    @Override
    @Async("threadPoolExecutor")
    public CompletableFuture<Page<UserDto>> getAllUser(Pageable paginador) throws Exception {

        var pageEntity = userRepository.findAllByActive(Constants.USER_ACTIVE, paginador);
        return CompletableFuture.completedFuture(pageEntity.map(userEntity -> this.modelMapper.map(userEntity, UserDto.class)));

    }

    @Override
    @Async("threadPoolExecutor")
    public CompletableFuture<UserDto> saveUser(UserDto user) throws Exception {
        var userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setState(Constants.USER_UP);
        userEntity.setActive(Constants.USER_ACTIVE);
        userEntity.setDateCreate(LocalDateTime.now());

        var newUser = this.saveUsers(userEntity);

        return CompletableFuture.completedFuture(modelMapper.map(newUser, UserDto.class));
    }

    @Override
    @Async("threadPoolExecutor")
    public CompletableFuture<UserDto> updateUser(UUID id, UserDto user) throws Exception {
        var userEntity = this.getOneUser(id);
        userEntity.setDocumentNumber(user.getDocumentNumber() != null ? user.getDocumentNumber() : userEntity.getDocumentNumber());
        userEntity.setFirtsName(user.getFirtsName() != null ? user.getFirtsName() : userEntity.getFirtsName());
        userEntity.setLastName(user.getLastName() != null ? user.getLastName() : userEntity.getLastName());
        userEntity.setState(user.getState() != null ? user.getState() : userEntity.getState());
        userEntity.setDateUpdate(LocalDateTime.now());
        var updateUser = this.saveUsers(userEntity);

        return CompletableFuture.completedFuture(modelMapper.map(updateUser, UserDto.class));
    }

    @Override
    @Async("threadPoolExecutor")
    public CompletableFuture<UUID> deleteUser(UUID id) throws Exception {
        var userEntity = this.getOneUser(id);
        userEntity.setActive(Constants.USER_INACTIVE);
        UserEntity deleteUser = this.saveUsers(userEntity);
        return CompletableFuture.completedFuture(UUID.fromString(deleteUser.getId()));
    }

    private synchronized UserEntity saveUsers(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    private synchronized UserEntity getOneUser(UUID id){
        return userRepository.findByIdActive(id.toString()).orElseThrow(() -> {
            log.error("No se encontro registro para el ID: {}", id.toString());
            throw new EntityNotFoundException("No se encontro registro para el ID "+ id.toString());
        });
    }
}
