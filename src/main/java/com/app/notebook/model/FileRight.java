package com.app.notebook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * notebook-api Created by Catalin on 10/21/2020
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_rights")
public class FileRight extends Auditable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", referencedColumnName = "id", nullable = false)
    private File file;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "access_type", nullable = false)
    private AccessType type;
}
