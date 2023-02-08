package org.example.presentation;

import lombok.RequiredArgsConstructor;
import org.example.persistence.SampleDaoRepository;
import org.example.persistence.dao.SampleDao;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sample-dao")
@RequiredArgsConstructor
public class MainController {
    private final SampleDaoRepository repository;

    @GetMapping("/find/{id}")
    public Mono<SampleDao> find(@PathVariable Long id) {
        return Mono.fromCompletionStage(repository.findById(id));
    }

    @PostMapping("/save")
    public Mono<SampleDao> save(@RequestBody SampleDao dao) {
        return Mono.fromCompletionStage(repository.save(dao));
    }
}
