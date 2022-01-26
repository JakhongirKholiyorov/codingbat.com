package com.codingbat.codingbat.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private String answerText;
    private Integer userId;
    private Integer taskId;

}
