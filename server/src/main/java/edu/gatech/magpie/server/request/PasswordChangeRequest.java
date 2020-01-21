package edu.gatech.magpie.server.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordChangeRequest {

  private String username;

  private String oldPassword;

  private String newPassword;

}
