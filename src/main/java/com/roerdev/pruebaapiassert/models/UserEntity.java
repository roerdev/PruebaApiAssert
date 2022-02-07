package com.roerdev.pruebaapiassert.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "h2_user")
@Entity
public class UserEntity {

    @Id
    @Column(name = "h2_user_id", nullable = false)
    private String id;

    @Column(name = "h2_user_document", length = 20, nullable = false)
    private String documentNumber;

    @Column(name = "h2_user_firts_name", length = 50, nullable = false)
    private String firtsName;

    @Column(name = "h2_user_last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "h2_user_cod_state", nullable = false)
    private Integer state;

    @Column(name = "h2_user_cod_active", nullable = false)
    private Integer active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "h2_user_date_create", nullable = false)
    private LocalDateTime dateCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "h2_user_date_update")
    private LocalDateTime dateUpdate;

}
