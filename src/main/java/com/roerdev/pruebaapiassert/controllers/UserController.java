package com.roerdev.pruebaapiassert.controllers;

import com.roerdev.pruebaapiassert.dto.ResponseDTO;
import com.roerdev.pruebaapiassert.exceptions.BadRequestException;
import com.roerdev.pruebaapiassert.utils.Constants;
import com.roerdev.pruebaapiassert.dto.UserDto;
import com.roerdev.pruebaapiassert.exceptions.EntityNotFoundException;
import com.roerdev.pruebaapiassert.services.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "Servicio para obtener usuario por ID")
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUser (@PathVariable UUID id){

        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.OK.getReasonPhrase(),
                    this.userService.getUser(id).get(),
                    Constants.MESSAGE
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new EntityNotFoundException("");
        }
    }

    @ApiOperation(value = "Servicio para obtener todos los usuarios registrados")
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllUser(Pageable paginador) {
        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.OK.getReasonPhrase(),
                    this.userService.getAllUser(paginador).get(),
                    Constants.MESSAGE
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new EntityNotFoundException("");
        }
    }

    @ApiOperation(value = "Servicio para guardar un nuevo usuario")
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){

        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.OK.getReasonPhrase(),
                    this.userService.saveUser(user).get(),
                    Constants.MESSAGE
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new BadRequestException("Failed to save.");
        }
    }

    @ApiOperation(value = "Servicio para actualizar un usuario")
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUser (@PathVariable UUID id, @RequestBody UserDto user){

        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.OK.getReasonPhrase(),
                    this.userService.updateUser(id, user).get(),
                    Constants.MESSAGE
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new BadRequestException("Failed to save.");
        }
    }

    @ApiOperation(value = "Servicio para eliminar logicamente un usuario")
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser (@PathVariable UUID id){

        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.OK.getReasonPhrase(),
                    Constants.MESSAGE,
                    this.userService.deleteUser(id).get()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new BadRequestException("Failed to save.");
        }
    }
}
