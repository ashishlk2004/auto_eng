package uk.ac.york.eng2.reactive.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import uk.ac.york.eng2.reactive.domain.TopicSlot;
import uk.ac.york.eng2.reactive.repositories.TopicSlotRepository;

import java.util.Optional;

@Tag(name = "topic-slots")
@Controller("/topic-slots")
public class TopicSlotsController {

    @Inject
    private TopicSlotRepository repo;

    @Get
    public Iterable<TopicSlot> list(@QueryValue Optional<String> topic) {
        if (topic.isPresent()) {
            return repo.findByTopicName(topic.get());
        }
        return repo.findAll();
    }

    @Get("/{topic}/{slot}")
    public HttpResponse<TopicSlot> getSlot(@PathVariable String topic, @PathVariable String slot) {
        return repo.findByTopicNameAndSlotName(topic, slot)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }
}
