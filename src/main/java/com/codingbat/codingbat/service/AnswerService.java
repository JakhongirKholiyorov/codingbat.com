package com.codingbat.codingbat.service;

import com.codingbat.codingbat.dto.AnswerDto;
import com.codingbat.codingbat.entity.Answer;
import com.codingbat.codingbat.entity.Task;
import com.codingbat.codingbat.entity.User;
import com.codingbat.codingbat.repository.AnswerRepository;
import com.codingbat.codingbat.repository.TaskRepository;
import com.codingbat.codingbat.repository.UserRepository;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;


    /**
     * JAVOB QO'SHISH
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    public ApiResponse addAnswer(AnswerDto answerDto){
        if (!userRepository.existsById(answerDto.getUserId())){
            return new ApiResponse("Bunday user topilmadi.",false);
        }

        if (!taskRepository.existsById(answerDto.getTaskId())){
            return new ApiResponse("Bunday topshiriq topilmadi.",false);
        }

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());

        Answer answer = new Answer();
        answer.setAnswerText(answerDto.getAnswerText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Javob saqlandi.",true);
    }


    /**
     * JAVOBNI TAHRIRLASH
     * @param id Integer
     * @param answerDto AnswerDto
     * @return ApiResponse
     */
    public ApiResponse editAnswer(Integer id, AnswerDto answerDto){
        if (!answerRepository.existsById(id)){
            return new ApiResponse("Bunday javob topilmadi.",false);
        }

        if (!userRepository.existsById(answerDto.getUserId())){
            return new ApiResponse("Bunday user topilmadi.",false);
        }

        if (!taskRepository.existsById(answerDto.getTaskId())){
            return new ApiResponse("Bunday topshiriq topilmadi.",false);
        }

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        Answer answer = optionalAnswer.get();
        answer.setAnswerText(answerDto.getAnswerText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Javob tahrirlandi.",true);
    }


    /**
     * JAVOBLAR RO'YXATINI QAYTARISH
     * @return List
     */
    public List<Answer> getAnswerList(){
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }


    /**
     * JAVOBNI QAYTARUSH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getAnswerById(Integer id){
        if (!answerRepository.existsById(id)){
            return new ApiResponse("Bunday javob topilmadi.",false);
        }
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return new ApiResponse(optionalAnswer.get(),true);
    }


    /**
     * JAVOBNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteAnswer(Integer id){
        if (!answerRepository.existsById(id)){
            return new ApiResponse("Bunday javob topilmadi.",false);
        }
        answerRepository.deleteById(id);
        return new ApiResponse("Javob o'chirildi.",true);
    }


}


















