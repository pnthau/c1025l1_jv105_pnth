package com.example.display_ingredients.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @PostMapping
    @ResponseBody
    public String handlerChooseIngredients(@RequestParam(value = "condiment") String[] condiment){
        return Arrays.toString(condiment) ;
    }
}
