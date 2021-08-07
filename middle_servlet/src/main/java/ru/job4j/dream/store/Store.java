package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    void save(Post post);

    Post findById(int id);

    Collection<Candidate> findAllCandidates();

    void save(Candidate candidate);

    Candidate findByIdCandidate(int id);

    void deleteCandidate(int id);

    void save(User user);

    User findByEmail(String email);
}
