package org.dnyanyog.controller;

import org.dnyanyog.dto.User;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

  @Autowired UserManagementService userService;

  @PostMapping(
      path = "/api/v1/auth/user",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public UserResponse addUpdateUser(@RequestBody UserRequest UserRequest) {
    return userService.addUpdateUser(UserRequest);
  }

  @GetMapping(path = "/api/v1/auth/user/{userId}")
  public User getSingleUser(@PathVariable Long userId) {
    return userService.getSingleUser(userId);
  }
}
