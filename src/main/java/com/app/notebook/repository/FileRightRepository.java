package com.app.notebook.repository;

import com.app.notebook.model.FileRight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRightRepository extends JpaRepository<FileRight, UUID> {
}
