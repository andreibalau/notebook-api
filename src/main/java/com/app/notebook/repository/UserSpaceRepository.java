package com.app.notebook.repository;

import com.app.notebook.model.UserSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSpaceRepository extends JpaRepository<UserSpace, UUID> {
}
