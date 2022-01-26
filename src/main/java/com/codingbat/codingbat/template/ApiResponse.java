package com.codingbat.codingbat.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private boolean success;
    private String massage;
    private Object object;

    public ApiResponse(String massage, boolean success) {
        this.success = success;
        this.massage = massage;
    }

    public ApiResponse( Object object,boolean success) {
        this.success = success;
        this.object = object;
    }

    public ApiResponse(Object object) {
        this.object = object;
    }
}
