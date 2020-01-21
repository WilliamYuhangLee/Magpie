package com.example.Magpie.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PasswordChange {
    private String username;

    private String oldPassword;

    private String newPassword;
}
