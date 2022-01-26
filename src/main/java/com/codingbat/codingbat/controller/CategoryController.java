package com.codingbat.codingbat.controller;

import com.codingbat.codingbat.dto.CategoryDto;
import com.codingbat.codingbat.entity.Category;
import com.codingbat.codingbat.service.CategoryService;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    /**
     * KATEGORYANI QO'SHISH
     * @param categoryDto CategoryDto
     * @return ApiREsponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * KATEGORYANI TAHRIRLASH
     * @param categoryDto CategoryDto
     * @param id Integer
     * @return ApiResponse
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.editCategory(categoryDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * KATEGORYALAR RO'YXATINI QAYTARISH
     * @return List
     */
    @GetMapping
    public ResponseEntity<List<Category>> getCategoryList(){
        List<Category> categoryList = categoryService.getCategoryList();
        return ResponseEntity.ok(categoryList);
    }

    /**
     * KATEGORYA QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.getCategoryById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * KATEGOTYANI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
