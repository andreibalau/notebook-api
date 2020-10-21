package com.app.notebook.service.user;

import com.app.notebook.model.dto.CreateUserDto;

/** notebook-api Created by Catalin on 10/21/2020 */
public interface UserCreator {
  void create(CreateUserDto createUserDto);
}
