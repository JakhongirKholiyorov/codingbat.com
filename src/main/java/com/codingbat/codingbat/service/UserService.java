package com.codingbat.codingbat.service;

import com.codingbat.codingbat.entity.User;
import com.codingbat.codingbat.repository.UserRepository;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    /**
     * USER QO'SHISH
     * @param userDto User
     * @return ApiResponse
     */
    public ApiResponse addUser(User userDto){
        if (userRepository.existsByEmailAndPassword(userDto.getEmail(),userDto.getPassword())){
            return new ApiResponse("Bunday user mavjud.",false);
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return new ApiResponse("User saqlandi,",true);

    }


    /**
     * USERNI TAHRIRLASH
     * @param id Integer
     * @param userDto User
     * @return ApiResponse
     */
    public ApiResponse editUser(Integer id, User userDto){
        if (!userRepository.existsById(id)){
            return new ApiResponse("Bunday user topilmadi.",false);
        }

        if (userRepository.existsByEmailAndPassword(userDto.getEmail(),userDto.getPassword())){
            return new ApiResponse("Bunday user mavjud.",false);
        }

        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return new ApiResponse("User saqlandi,",true);
    }


    /**
     * USERLAR RO'YXATINI QAYTARISH
     * @return List
     */
    public List<User> getUser(){
        List<User> userList = userRepository.findAll();
        return userList;
    }


    /**
     * USER QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getUserById(Integer id){
        if (!userRepository.existsById(id)){
            return new ApiResponse("Bunday user topilmadi.",false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        return new ApiResponse(optionalUser.get(),true);
    }


    /**
     * USERNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteUser(Integer id){
        if (!userRepository.existsById(id)){
            return new ApiResponse("Bunday user topilmadi.",false);
        }
        userRepository.deleteById(id);
        return new ApiResponse("User o'chirildi.",true);
    }




}

























