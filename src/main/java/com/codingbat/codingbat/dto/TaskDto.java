package com.codingbat.codingbat.dto;

import lombok.Data;

@Data
public class TaskDto {

    private Integer categoryId;
    private String name;
    private String condition;
    private String example;

}
