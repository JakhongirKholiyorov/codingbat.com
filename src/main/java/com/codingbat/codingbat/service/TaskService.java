package com.codingbat.codingbat.service;

import com.codingbat.codingbat.dto.TaskDto;
import com.codingbat.codingbat.entity.Category;
import com.codingbat.codingbat.entity.Task;
import com.codingbat.codingbat.repository.CategoryRepository;
import com.codingbat.codingbat.repository.LanguageRepository;
import com.codingbat.codingbat.repository.TaskRepository;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;


    /**
     * TOPSHIRIQNI SAQLASH
     * @param taskDto TaskDto
     * @return ApiResponse
     */
    public ApiResponse addTask(TaskDto taskDto){

        if (taskRepository.existsByNameAndConditionAndCategoryId(taskDto.getName(),taskDto.getCondition(),taskDto.getCategoryId())){
            return new ApiResponse("Bu topshiriq mavjud",false);
        }

        if (!categoryRepository.existsById(taskDto.getCategoryId())){
            return new ApiResponse("Bunday kategorya topilmadi.",false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());

        Task task = new Task();
        task.setCondition(taskDto.getCondition());
        task.setExample(taskDto.getExample());
        task.setName(taskDto.getName());
        task.setCategory(optionalCategory.get());
        taskRepository.save(task);

        return new ApiResponse("Topshiriq saqlandi.",true);

    }


    /**
     * TOPSHIRIQNI TAHRIRLASH
     * @param taskDto TaskDto
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse editTask(TaskDto taskDto, Integer id){

        if (!taskRepository.existsById(id)){
            return new ApiResponse("Bunday topshiriq topilmadi.",false);
        }

        if (taskRepository.existsByNameAndConditionAndCategoryId(taskDto.getName(),taskDto.getCondition(),taskDto.getCategoryId())){
            return new ApiResponse("Bu topshiriq mavjud",false);
        }

        if (!categoryRepository.existsById(taskDto.getCategoryId())){
            return new ApiResponse("Bunday kategorya topilmadi.",false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        Optional<Task> optionalTask = taskRepository.findById(id);

        Task task = optionalTask.get();
        task.setCondition(taskDto.getCondition());
        task.setExample(taskDto.getExample());
        task.setName(taskDto.getName());
        task.setCategory(optionalCategory.get());
        taskRepository.save(task);

        return new ApiResponse("Topshiriq tahrirlandi.",true);

    }


    /**
     * TOPSHIRIQLAR TO'YXATINI QAYTARISH
     * @return List
     */
    public List<Task> getTaskList(){
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }


    /**
     * TOPSHIRIQNI QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getTaskById(Integer id){
        if (!taskRepository.existsById(id)){
            return new ApiResponse("Bunday topshiriq topilmadi.",false);
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        return new ApiResponse(optionalTask.get(),true);
    }


    /**
     * TOPSHIRIQNI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteTask(Integer id){
        if (!taskRepository.existsById(id)){
            return new ApiResponse("Bunday topshiriq topilmadi.",false);
        }
        taskRepository.deleteById(id);
        return new ApiResponse("Topshiriq oo'chirildi.",true);
    }





}
