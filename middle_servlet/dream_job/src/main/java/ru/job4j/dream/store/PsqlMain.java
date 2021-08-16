package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job", LocalDateTime.now()));
        Post postNew = store.findById(1);
        postNew.setName("New Java Job");
        store.save(postNew);
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        store.save(new Candidate(0, "Java Junior", 1, LocalDateTime.now()));
        Candidate candidateNew = store.findByIdCandidate(1);
        candidateNew.setName("Java Middle");
        store.save(candidateNew);
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
    }
}
