package com.example.local_time_nations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
@RequestMapping("/time")
public class TimeController {

    @GetMapping()
    public String getTimeByTimeZone(@RequestParam(value = "city", defaultValue = "Asia/Ho_Chi_Minh") String city,
                                    Model model){
        Date date = new Date();
        TimeZone localTimeZone = TimeZone.getDefault();
        TimeZone targetTimeZone = TimeZone.getTimeZone(city);
        long timeDiff =  targetTimeZone.getRawOffset() - localTimeZone.getRawOffset();
        long targetTimeInMilliSeconds = date.getTime() + timeDiff;
        date.setTime(targetTimeInMilliSeconds);

        model.addAttribute("city", city);
        model.addAttribute("date", date);

        return "time";
    }
}
