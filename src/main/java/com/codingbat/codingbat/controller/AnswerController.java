package com.codingbat.codingbat.controller;

import com.codingbat.codingbat.dto.AnswerDto;
import com.codingbat.codingbat.entity.Answer;
import com.codingbat.codingbat.service.AnswerService;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/answer")
public class AnswerController {


    @Autowired
    AnswerService answerService;


    /**
     * JAVOB QO'SHISH
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addAnswer(@RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * JAVOBNI TAHRIRLASH
     * @param id Integer
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editAnswer(@RequestBody AnswerDto answerDto, @PathVariable Integer id ){
        ApiResponse apiResponse = answerService.editAnswer(id, answerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    /**
     * JAVOBLAR RO'YXATINI QAYTARISH
     * @return List
     */
    @GetMapping
    public ResponseEntity<List<Answer>> getAnswerList(){
        List<Answer> answerList = answerService.getAnswerList();
        return ResponseEntity.ok(answerList);
    }

    /**
     * JAVOBNI QAYTARUSH
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getAnswerById(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.getAnswerById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * JAVOBNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
