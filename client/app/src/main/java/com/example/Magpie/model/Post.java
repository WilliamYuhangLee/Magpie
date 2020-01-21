package com.example.Magpie.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Post {

    private long id;

    private String username;

    private String content;

    private Date timeCreated;

    private Date timeModified;

}
