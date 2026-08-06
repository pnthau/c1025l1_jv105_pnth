package com.example.manager_music.controller;

import com.example.manager_music.dto.AudioForm;
import com.example.manager_music.entity.Audio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/audio")
public class AudioController {
    private static final List<Audio> audioList;
    static {
        audioList = new ArrayList<>();
        audioList.add(new Audio("aaaa","b",List.of("a"),"d"));
        audioList.add(new Audio("bbbbb","b",List.of("a"),"d"));
        audioList.add(new Audio("ccccc","b",List.of("a"),"d"));

    }
    @GetMapping("form")
    public String showFormUpload(Model model)
    {
        model.addAttribute("audioForm", new AudioForm());
        return "upload_file";
    }

    @PostMapping("/upload")
    public String uploadAudio(@ModelAttribute("audioForm") AudioForm audioForm, Model model) {
        MultipartFile file = audioForm.getAudioFile();

        String fileName = file.getOriginalFilename();
        String regex = ".+\\.(.mp3|.wav|.ogg |.m4p)";
        if (fileName != null && !fileName.matches(regex)) {
            model.addAttribute("message", "Chỉ cho phép file .mp3, .wav, .ogg, .m4p");
            return "upload_file";
        }

        long maxSize = 10 * 1024 * 1024;
        if(file.getSize() > maxSize)
        {
            model.addAttribute("message", "Chỉ cho phép file dung lượng >= 10MB");
            return "upload_file";
        }
        
        try {
            String uploadDir = "D:/Data/C1025L1-JV105/backend/module_4/ss04_spring_mvc_data_binding/bai_tap/manager_music/src/main/resources/static/audios/";
            File dest = new File(uploadDir + fileName);

            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // Lưu file vật lý vào ổ cứng
            file.transferTo(dest);

            Audio newAudio = new Audio();
            newAudio.setName(audioForm.getName());
            newAudio.setSinger(audioForm.getSinger());
            newAudio.setTypes(audioForm.getTypes());
            
            newAudio.setPath("/audios/" + fileName);
            
            audioList.add(newAudio);
            
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Upload thất bại!");
            return "upload_file";
        }

        return "redirect:/audio/list";
    }

    @GetMapping("/list")
    public String findAll(Model model)
    {
        model.addAttribute("audioList", audioList);
        return "list";
    }
}
