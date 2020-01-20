package edu.gatech.magpie.server.repository;

import edu.gatech.magpie.server.model.Account;
import edu.gatech.magpie.server.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> getAllByCreator(Account creator);
}
