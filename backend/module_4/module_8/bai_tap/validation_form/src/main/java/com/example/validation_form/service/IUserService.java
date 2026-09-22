package com.example.validation_form.service;

import com.example.validation_form.dto.UserRequestDTO;
import com.example.validation_form.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> getAll(Pageable pageable);
    boolean save(UserRequestDTO userRequestDTO);
    boolean isExitEmail(String email);
}
