package com.app.notebook.repository;

import com.app.notebook.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/** notebook-api Created by Catalin on 10/21/2020 */
public interface DocumentRepository extends JpaRepository<Document, UUID> {}
