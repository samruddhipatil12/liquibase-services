package org.dnyanyog.service;

import org.dnyanyog.dto.User;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;

public interface UserManagementService {

  public UserResponse addUpdateUser(UserRequest request);

  public User getSingleUser(Long userId);
}
