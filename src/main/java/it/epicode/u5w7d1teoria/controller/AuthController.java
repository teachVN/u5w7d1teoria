package it.epicode.u5w7d1teoria.controller;

import it.epicode.u5w7d1teoria.dto.UserDto;
import it.epicode.u5w7d1teoria.dto.UserLoginDto;
import it.epicode.u5w7d1teoria.exception.BadRequestException;
import it.epicode.u5w7d1teoria.service.AuthService;
import it.epicode.u5w7d1teoria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return userService.saveUser(userDto);
    }
    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated UserLoginDto userLoginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return authService.authenticateUserAndCreateToken(userLoginDto);
    }



}
