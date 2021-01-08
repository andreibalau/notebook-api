package com.app.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * notebook-api Created by Catalin on 10/21/2020
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "subcategories")
public class Subcategory extends Auditable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
