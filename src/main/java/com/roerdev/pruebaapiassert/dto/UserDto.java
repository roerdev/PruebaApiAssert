package com.roerdev.pruebaapiassert.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String id;
    private String documentNumber;
    private String firtsName;
    private String lastName;
    private Integer state;
    private Integer active;
    private LocalDateTime dateCreate;
    private LocalDateTime dateUpdate;

}
