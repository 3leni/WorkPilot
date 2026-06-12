package com.workpilot_backend.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public  class UserController{

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody UserDTO request){
        userService.createUser(request);
    }
    @PostMapping("/login")
    public void loginUser(@RequestBody UserDTO request){
        userService.login(request);
    }
}