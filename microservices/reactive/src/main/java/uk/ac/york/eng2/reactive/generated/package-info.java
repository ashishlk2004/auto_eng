/**
 * Companion classes for the RCL model-to-text generator (Part 2), supporting the code-generation
 * round-trip for the {@code homeControl.rcl} scenario.
 *
 * <p>The generator ({@code uk.ac.york.cs.eng2.rcl.generator}) produces, per Kafka topic, a producer
 * ({@code @KafkaClient}), a topic factory ({@code @Factory} {@code NewTopic} bean), and a consumer
 * ({@code @KafkaListener}) into the {@code producers}, {@code factories} and {@code consumers}
 * sub-packages. Each generated consumer persists the topic's slots via
 * {@link uk.ac.york.eng2.reactive.util.TopicSlotService} and then delegates to the hand-written
 * {@code *Activation} bean for every component that the topic triggers.</p>
 *
 * <p>The hand-written halves of that round-trip live here and are never overwritten by the
 * generator:</p>
 * <ul>
 *   <li>{@code dto.*Record} &mdash; the Kafka payload types referenced by the generated producers
 *       and consumers (one per topic).</li>
 *   <li>{@code activations.*Activation} &mdash; the manually-completed behaviour the generated
 *       consumers delegate to (one {@code onTrigger} overload per triggering topic).</li>
 * </ul>
 *
 * <p>These companions make the generator's output compile and give its delegation a concrete
 * target. They are intentionally inert in the running service: nothing under {@code src/main}
 * instantiates them, so the live RCM pipeline remains the hand-written components in
 * {@code uk.ac.york.eng2.reactive.components}. Running the generator emits the matching
 * producers/factories/consumers alongside these classes.</p>
 */
package uk.ac.york.eng2.reactive.generated;
