package edu.gatech.magpie.server.service;

import edu.gatech.magpie.server.dto.PostDto;
import java.util.List;

public interface PostService {

  PostDto create(String username, String content);

  PostDto get(long postId);

  List<PostDto> get(String username);

  PostDto update(long postId, String content);

  boolean delete(long postId);
}
