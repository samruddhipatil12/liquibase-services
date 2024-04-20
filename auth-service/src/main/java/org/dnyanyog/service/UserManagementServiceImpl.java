package org.dnyanyog.service;

import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.User;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

  @Autowired UsersRepository userRepo;

  public UserResponse addUpdateUser(UserRequest request) {
    UserResponse response = new UserResponse();

    Users usersTable = new Users();

    usersTable.setAge(request.getAge());
    usersTable.setEmail(request.getEmail());
    usersTable.setPassword(request.getPassword());
    usersTable.setUsername(request.getUsername());

    usersTable = userRepo.save(usersTable);

    response.setMessage(ResponseCode.USER_ADD.getMessage());
    response.setStatus(ResponseCode.USER_ADD.getStatus());
    response.setUserId(usersTable.getUserId());

    return response;
  }

  public User getSingleUser(Long userId) {
    User userResponse = new User();

    java.util.Optional<Users> receivedData = userRepo.findById(userId);

    if (receivedData.isEmpty()) {
      userResponse.setStatus(ResponseCode.USER_NOT_FOUND.getStatus());
      userResponse.setMessage(ResponseCode.USER_NOT_FOUND.getMessage());
    } else {
      Users user = receivedData.get();
      userResponse.setStatus(ResponseCode.USER_FOUND.getStatus());
      userResponse.setMessage(ResponseCode.USER_FOUND.getMessage());
      userResponse.setEmail(user.getEmail());
      userResponse.setUsername(user.getUsername());
      userResponse.setPassword(user.getPassword());
      userResponse.setUserId(user.getUserId());
      userResponse.setAge(user.getAge());
    }
    return userResponse;
  }
}
