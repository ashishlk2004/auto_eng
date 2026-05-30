package uk.ac.york.eng2.reactive.util;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.reactive.domain.TopicSlot;
import uk.ac.york.eng2.reactive.repositories.TopicSlotRepository;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Transactional helper that does the actual SELECT + INSERT/UPDATE for a topic slot.
 *
 * Kept as a separate bean from {@link TopicSlotService} so that the facade can serialise
 * calls with a synchronized block *before* the @Transactional proxy starts a transaction.
 * Without that ordering, two consumer threads can both reach the SELECT before either
 * commits its INSERT, and the second one trips the (topic_name, slot_name) unique
 * constraint.
 */
@Singleton
public class TopicSlotPersister {

    @Inject
    private TopicSlotRepository repo;

    @Transactional
    public TopicSlot upsert(String topicName, String slotName, Consumer<TopicSlot> mutator) {
        Optional<TopicSlot> existing = repo.findByTopicNameAndSlotName(topicName, slotName);
        TopicSlot slot = existing.orElseGet(() -> {
            TopicSlot s = new TopicSlot();
            s.setTopicName(topicName);
            s.setSlotName(slotName);
            return s;
        });
        mutator.accept(slot);
        return existing.isPresent() ? repo.update(slot) : repo.save(slot);
    }
}
