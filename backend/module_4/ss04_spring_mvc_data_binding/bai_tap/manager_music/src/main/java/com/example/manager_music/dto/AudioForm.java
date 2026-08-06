package com.example.manager_music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioForm {
    private String name;
    private String singer;
    private List<String> types;
    private MultipartFile audioFile; // Hứng file từ input type="file"
}
