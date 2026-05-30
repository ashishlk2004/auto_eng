package uk.ac.york.eng2.reactive.util;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.reactive.domain.Component;
import uk.ac.york.eng2.reactive.repositories.ComponentRepository;

@Singleton
public class ComponentRegistration {

    @Inject
    private ComponentRepository componentRepository;

    @Transactional
    public Component ensureExists(String name) {
        return componentRepository.findByName(name).orElseGet(() -> {
            Component c = new Component();
            c.setName(name);
            return componentRepository.save(c);
        });
    }
}
