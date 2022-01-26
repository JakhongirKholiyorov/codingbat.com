package com.codingbat.codingbat.service;

import com.codingbat.codingbat.entity.Language;
import com.codingbat.codingbat.repository.LanguageRepository;
import com.codingbat.codingbat.template.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;


    /**
     * DASTURLSH TILI QO'SHISH
     * @param languageDto Language
     * @return ApiResponse
     */
    public ApiResponse addLanguage(Language languageDto){
        if (languageRepository.existsByName(languageDto.getName())){
            return new ApiResponse("Bunday dasturlash tili mavjud",false);
        }
        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Dasturlash tili saqlandi",true);
    }


    /**
     * DASTURLASH TILINI TAHRIRLASH
     * @param id Integer
     * @param languageDto Language
     * @return ApiResponse
     */
    public ApiResponse editLanguage(Integer id, Language languageDto){

        if (!languageRepository.existsById(id)){
            return new ApiResponse("Bunday dasturlash tili topilmadi.",false);
        }

        if (languageRepository.existsByName(languageDto.getName())){
            return new ApiResponse("Bunday dasturlash tili mavjud",false);
        }


        Optional<Language> optionalLanguage = languageRepository.findById(id);

        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Dasturlash tili tahrirlandi",true);
    }


    /**
     * DASTURLASH TILINI RO'YXATINI QAYTARISH
     * @return List
     */
    public List<Language> getLanguageList(){
        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }


    /**
     * DASTURLSH TILINI ID ORQALI QAYTARISH
     * @param id Integer
     * @return ApiRsponse
     */
    public ApiResponse getLanguageById(Integer id){
        if (!languageRepository.existsById(id)){
            return new ApiResponse("Bunday dasturlash tili topilmadi.",false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return new ApiResponse(optionalLanguage.get(),true);
    }


    /**
     * DASTURLASH TILINI O'CHIRISH
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteLanguage(Integer id){
        if (!languageRepository.existsById(id)){
            return new ApiResponse("Bunday dasturlash tili topilmadi.",false);
        }
        languageRepository.deleteById(id);
        return new ApiResponse("Dasturlash tili o'chirildi.",true);
    }




}
