package edu.gatech.magpie.server.service;

import edu.gatech.magpie.server.dto.PostDto;

public interface PostService {

  PostDto create(String username, String content);

  PostDto update(long postId, String content);

  boolean delete(long postId);
}
