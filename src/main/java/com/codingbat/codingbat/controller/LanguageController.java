package com.codingbat.codingbat.controller;

import com.codingbat.codingbat.entity.Language;
import com.codingbat.codingbat.service.LanguageService;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/language")
public class LanguageController {


    @Autowired
    LanguageService languageService;


    /**
     * DASTURLSH TILI QO'SHISH
     * @param languageDto Language
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addLanguage(@RequestBody Language languageDto){
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    /**
     * DASTURLASH TILINI TAHRIRLASH
     * @param id Integer
     * @param languageDto Language
     * @return ResponseEntity<ApiResponse>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@RequestBody Language languageDto, @PathVariable Integer id){
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


    /**
     * DASTURLASH TILINI RO'YXATINI QAYTARISH
     * @return List
     */
    @GetMapping
    public ResponseEntity<List<Language>> getLanguageList(){
        List<Language> languageList = languageService.getLanguageList();
        return ResponseEntity.ok(languageList);
    }


    /**
     * DASTURLSH TILINI ID ORQALI QAYTARISH
     * @param id Integer
     * @return ApiRsponse
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getLanguageById(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.getLanguageById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


    /**
     * DASTURLASH TILINI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }






}























