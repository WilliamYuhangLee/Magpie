package edu.gatech.magpie.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.gatech.magpie.server.model.Post;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDto {

  private long id;

  private String username;

  private String content;

  private Date timeCreated;

  private Date timeModified;

  public PostDto(Post post) {
    this.id = post.getId();
    this.username = post.getCreator().getUsername();
    this.content = post.getContent();
    this.timeCreated = post.getTimeCreated();
    this.timeModified = post.getTimeModified();
  }
}
