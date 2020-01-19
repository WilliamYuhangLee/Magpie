package edu.gatech.magpie.server.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "posts")
public class Post {

  @Id @GeneratedValue private long id;

  @ManyToOne private Account creator;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Temporal(TemporalType.TIMESTAMP)
  private Date timeCreated;

  @Temporal(TemporalType.TIMESTAMP)
  private Date timeModified;
}
