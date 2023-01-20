package com.example.wkshp12.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.*;

@Getter
@Setter
@Controller
public class WorkshopController {
    
    int NUM_MIN = 0;
    int NUM_MAX = 30;
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/results")
    public String results(@RequestParam String number, Model model1) { //create a model called model1

        List<Integer> range = IntStream.rangeClosed(NUM_MIN, NUM_MAX).boxed().toList();
        range = new ArrayList<>(range);
        List<String> images = new ArrayList<>();

        try{
            for(int i = 0; i < Integer.parseInt(number); i++) {
                int randint = ThreadLocalRandom.current().nextInt(0, range.size());   //to generate a random number from 0 to 30
                images.add("number" + range.get(randint) + ".jpg");
                range.remove(randint);
            }
        } catch (NumberFormatException e) {
            images = null;
        } catch (Exception e) {
            images = null;
        }
        
        model1.addAttribute("images", images);
        // model.addAttribute("number", number);

        return "results";
    }
    
}
