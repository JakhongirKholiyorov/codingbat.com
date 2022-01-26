package com.codingbat.codingbat.service;

import com.codingbat.codingbat.dto.CategoryDto;
import com.codingbat.codingbat.entity.Category;
import com.codingbat.codingbat.entity.Language;
import com.codingbat.codingbat.repository.CategoryRepository;
import com.codingbat.codingbat.repository.LanguageRepository;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LanguageRepository languageRepository;


    /**
     * KATEGORYANI QO'SHISH
     * @param categoryDto CategoryDto
     * @return ApiREsponse
     */
    public ApiResponse addCategory(CategoryDto categoryDto){

        if (categoryRepository.existsByNameAndLanguageId(categoryDto.getName(),categoryDto.getLanguageId())){
            return new ApiResponse("Bunday kategorya mavjud",false);
        }

        if (!languageRepository.existsById(categoryDto.getLanguageId())){
            return new ApiResponse("Bunday dasturlash tili topilmadi.",false);
        }

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(optionalLanguage.get());
        categoryRepository.save(category);
        return new ApiResponse("Kategorya saqlandi.",true);
    }


    /**
     * KATEGORYANI TAHRIRLASH
     * @param categoryDto CategoryDto
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse editCategory(CategoryDto categoryDto, Integer id){

        if (!categoryRepository.existsById(id)){
            return new ApiResponse("Bunday kategorya topilmadi.",false);
        }

        if (categoryRepository.existsByNameAndLanguageId(categoryDto.getName(),categoryDto.getLanguageId())){
            return new ApiResponse("Bunday kategorya mavjud",false);
        }

        if (!languageRepository.existsById(categoryDto.getLanguageId())){
            return new ApiResponse("Bunday dasturlash tili topilmadi.",false);
        }


        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(optionalLanguage.get());
        categoryRepository.save(category);
        return new ApiResponse("Kategorya tahrirlandi.",true);
    }


    /**
     * KATEGORYALAR RO'YXATINI QAYTARISH
     * @return List
     */
    public List<Category> getCategoryList(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }


    /**
     * KATEGORYA QAYTARISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse getCategoryById(Integer id){
        if (!categoryRepository.existsById(id)){
            return new ApiResponse("Bunday kategorya topilmadi.",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return new ApiResponse(optionalCategory.get(),true);
    }


    /**
     * KATEGOTYANI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteCategory(Integer id){
        if (!categoryRepository.existsById(id)){
            return new ApiResponse("Bunday kategorya topilmadi.",false);
        }
        categoryRepository.deleteById(id);
        return new ApiResponse("Kategorya o'chirildi.",true);
    }

}



















