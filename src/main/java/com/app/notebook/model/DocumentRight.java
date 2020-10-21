package com.app.notebook.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/** notebook-api Created by Catalin on 10/21/2020 */
@Getter
@Setter
@Entity
@Table(name = "document_rights")
public class DocumentRight {

  @Id @GeneratedValue private UUID id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "document_id", nullable = false)
  private Document document;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "access_type", nullable = false)
  private AccessType type;
}
