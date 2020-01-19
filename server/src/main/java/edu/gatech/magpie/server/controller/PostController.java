package edu.gatech.magpie.server.controller;

import edu.gatech.magpie.server.dto.PostDto;
import edu.gatech.magpie.server.request.PostRequest;
import edu.gatech.magpie.server.response.Response;
import edu.gatech.magpie.server.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public Response create(@RequestParam String username, @RequestBody PostRequest request) {
    PostDto postDto = postService.create(username, request.getContent());
    return Response.withStatus(HttpStatus.CREATED).setPayload(postDto);
  }

  @RequestMapping(value = "/{postId}", method = RequestMethod.PUT)
  public Response edit(@PathVariable long postId, @RequestBody PostRequest request) {
    PostDto postDto = postService.update(postId, request.getContent());
    return Response.withStatus(HttpStatus.OK).setPayload(postDto);
  }

  @RequestMapping(value = "/{postId}", method = RequestMethod.DELETE)
  public Response delete(@PathVariable long postId) {
    if (postService.delete(postId)) {
      return Response.withStatus(HttpStatus.NO_CONTENT);
    } else {
      return Response.withStatus(HttpStatus.NOT_MODIFIED);
    }
  }
}
