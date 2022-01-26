package com.codingbat.codingbat.controller;

import com.codingbat.codingbat.entity.User;
import com.codingbat.codingbat.service.UserService;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * USER QO'SHISH
     * @param userDto User
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@RequestBody User userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);

    }


    /**
     * USERNI TAHRIRLASH
     * @param id Integer
     * @param userDto User
     * @return ApiResponse
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> editUser(@RequestBody User userDto, @PathVariable Integer id){
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * USERLAR RO'YXATINI QAYTARISH
     * @return List
     */
    @GetMapping
    public ResponseEntity<List<User>> getUserList(){
        List<User> userList = userService.getUser();
        return ResponseEntity.ok(userList);
    }


    /**
     * USER QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Integer id){
        ApiResponse apiResponse = userService.getUserById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    /**
     * USERNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }




}
