package com.app.notebook.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_space")
@EnableJpaAuditing
public class UserSpace extends Auditable {

    @Id
    private UUID id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "dir_path", unique = true, nullable = false)
    private String dirPath;

    @JsonManagedReference
    @OneToMany(mappedBy = "userSpace", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();

}
