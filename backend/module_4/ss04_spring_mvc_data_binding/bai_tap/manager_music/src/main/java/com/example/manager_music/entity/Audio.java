package com.example.manager_music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audio {
    private String name;
    private String singer;
    private List<String> types;
    private String path;
}
