package it.epicode.u5w7d1teoria.service;

import it.epicode.u5w7d1teoria.dto.UserLoginDto;
import it.epicode.u5w7d1teoria.entity.User;
import it.epicode.u5w7d1teoria.exception.UnauthorizedException;
import it.epicode.u5w7d1teoria.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTool jwtTool;

    public String authenticateUserAndCreateToken(UserLoginDto userLoginDto){
        User user = userService.getUserByEmail(userLoginDto.getEmail());

        if(user.getPassword().equals(userLoginDto.getPassword())){
            return jwtTool.createToken(user);
        }
        else{
            throw  new UnauthorizedException("Error in authorization, relogin!");
        }
    }
}
