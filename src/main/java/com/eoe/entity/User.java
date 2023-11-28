package com.eoe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long UserID;

    private String UserName;

    private String Password;

    private String Email;

    private String PhoneNumber;

    private String Nickname;

    private String Avatar;
    // TimeStamp是java里面对应数据库DATETIME
    private Timestamp RegistrationTime;

    private Timestamp LastLoginDate;
}