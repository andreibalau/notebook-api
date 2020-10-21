package com.app.notebook.service.category;

import com.app.notebook.model.Category;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/** notebook-api Created by Catalin on 10/21/2020 */
public interface CategoryFinder {
  Category findById(UUID id);

  Iterable<Category> findAllById(Iterable<UUID> ids);

  Iterable<Category> findAll(PageRequest request);
}
