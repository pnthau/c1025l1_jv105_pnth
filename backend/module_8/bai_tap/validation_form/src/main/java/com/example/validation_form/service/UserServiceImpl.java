package com.example.validation_form.service;

import com.example.validation_form.dto.UserRequestDTO;
import com.example.validation_form.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.validation_form.repository.IUserRepository;

import java.time.Year;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService    {
    private final IUserRepository userRepository;
    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean save(UserRequestDTO requestDTO) {
        int calculatedYear = Year.now().getValue() - requestDTO.getAge();
        try{
            User entity  = User.builder()
                    .firstName(requestDTO.getFirstName())
                    .lastName(requestDTO.getLastName())
                    .email(requestDTO.getEmail())
                    .year(calculatedYear)
                    .build();
            userRepository.save(entity);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExitEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
