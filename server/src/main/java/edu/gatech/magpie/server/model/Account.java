package edu.gatech.magpie.server.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "accounts")
public class Account {

  @Id private String username;

  @NonNull
  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "creator")
  private List<Post> posts;
}
