package com.roerdev.pruebaapiassert.repository;

import com.roerdev.pruebaapiassert.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("select ue from UserEntity ue where ue.id = ?1 and ue.active = 1")
    Optional<UserEntity> findByIdActive(String s);

    @Query("select ue from UserEntity ue where ue.active = ?1")
    Page<UserEntity> findAllByActive(int active, Pageable pageable);
}
