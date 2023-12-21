package com.eoe.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/20 21:27
 * @Decription :
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapInfoUpdateFailException extends RuntimeException {
    private String  msg;
}
