package uk.ac.york.eng2.reactive.util;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import uk.ac.york.eng2.reactive.domain.TopicSlot;

import java.time.Instant;
import java.util.function.Consumer;

/**
 * Helper service responsible for upserting the most recent value of a topic slot
 * into the RCM database.
 *
 * The save* methods are synchronized so that two threads cannot both reach the
 * SELECT-before-INSERT region at once. The synchronized block is outside the
 * @Transactional boundary of {@link TopicSlotPersister} so that, by the time the
 * lock is released, the previous transaction has fully committed and the next
 * caller's SELECT will see the row.
 */
@Singleton
public class TopicSlotService {

    @Inject
    private TopicSlotPersister persister;

    private final Object lock = new Object();

    public TopicSlot upsert(String topicName, String slotName, Consumer<TopicSlot> mutator) {
        synchronized (lock) {
            return persister.upsert(topicName, slotName, mutator);
        }
    }

    public TopicSlot saveText(String topicName, String slotName, String value) {
        return upsert(topicName, slotName, s -> s.setTextValue(value));
    }

    public TopicSlot saveTimestamp(String topicName, String slotName, Instant value) {
        return upsert(topicName, slotName, s -> s.setTimestampValue(value));
    }

    public TopicSlot saveDouble(String topicName, String slotName, double value) {
        return upsert(topicName, slotName, s -> s.setDoubleValue(value));
    }

    public TopicSlot saveLong(String topicName, String slotName, long value) {
        return upsert(topicName, slotName, s -> s.setLongValue(value));
    }
}
