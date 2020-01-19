package edu.gatech.magpie.server.service;

import edu.gatech.magpie.server.dto.PostDto;
import edu.gatech.magpie.server.model.Account;
import edu.gatech.magpie.server.model.Post;
import edu.gatech.magpie.server.repository.AccountRepository;
import edu.gatech.magpie.server.repository.PostRepository;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final AccountRepository accountRepository;

  public PostServiceImpl(PostRepository postRepository, AccountRepository accountRepository) {
    this.postRepository = postRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public PostDto create(String username, String content) {
    Account creator =
        accountRepository
            .findById(username)
            .orElseThrow(() -> new RuntimeException("Invalid username <" + username + ">."));
    Date now = new Date();
    Post post =
        new Post().setCreator(creator).setTimeCreated(now).setTimeModified(now).setContent(content);
    postRepository.saveAndFlush(post);
    return new PostDto()
        .setId(post.getId())
        .setUsername(post.getCreator().getUsername())
        .setContent(post.getContent())
        .setTimeCreated(post.getTimeCreated())
        .setTimeModified(post.getTimeModified());
  }

  @Override
  public PostDto update(long postId, String content) {
    Post post =
        postRepository
            .findById(postId)
            .orElseThrow(() -> new RuntimeException("Invalid post ID <" + postId + ">."));
    post.setContent(content).setTimeModified(new Date());
    postRepository.saveAndFlush(post);
    return new PostDto()
        .setId(post.getId())
        .setUsername(post.getCreator().getUsername())
        .setContent(post.getContent())
        .setTimeCreated(post.getTimeCreated())
        .setTimeModified(post.getTimeModified());
  }

  @Override
  public boolean delete(long postId) {
    if (!postRepository.existsById(postId)) {
      return false;
    }
    postRepository.deleteById(postId);
    return true;
  }
}
