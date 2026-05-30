package uk.ac.york.eng2.reactive.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import uk.ac.york.eng2.reactive.domain.Component;
import uk.ac.york.eng2.reactive.repositories.ComponentRepository;

@Tag(name = "components")
@Controller("/components")
public class ComponentsController {

    @Inject
    private ComponentRepository componentRepository;

    @Get
    public Iterable<Component> list() {
        return componentRepository.findAll();
    }

    @Get("/{id}")
    public HttpResponse<Component> getComponent(@PathVariable Long id) {
        return componentRepository.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }
}
