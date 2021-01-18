package com.app.notebook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "files")
public class File extends Auditable {

    @Id
    private UUID id;

    @NotBlank
    @Column(name = "path", unique = true, nullable = false)
    private String path;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userspace_id", nullable = false)
    private UserSpace userSpace;

    @JsonManagedReference
    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FileRight> rights = new ArrayList<>();

    public void setRights(List<FileRight> rights) {
        this.rights = rights;
        rights.forEach(right -> right.setFile(this));
    }

}
