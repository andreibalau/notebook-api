package com.app.notebook.repository;

import com.app.notebook.model.File;
import com.app.notebook.model.UserSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * notebook-api Created by Catalin on 10/21/2020
 */
public interface FileRepository extends JpaRepository<File, UUID> {

    boolean existsBySpaceAndName(UserSpace space, String name);
}
