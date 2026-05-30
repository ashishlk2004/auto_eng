package uk.ac.york.eng2.iot.scheduled;

import io.micronaut.context.annotation.Value;
import io.micronaut.scheduling.annotation.Scheduled;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import uk.ac.york.eng2.iot.domain.Actuator;
import uk.ac.york.eng2.iot.domain.Reading;
import uk.ac.york.eng2.iot.domain.Sensor;
import uk.ac.york.eng2.iot.dto.ReadingDTO;
import uk.ac.york.eng2.iot.events.ReadingsProducer;
import uk.ac.york.eng2.iot.repositories.ActuatorRepository;
import uk.ac.york.eng2.iot.repositories.ReadingRepository;
import uk.ac.york.eng2.iot.repositories.SensorRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Simulation of temperature sensors. The simulated temperature drifts toward the outside
 * temperature when the room heater is off, and toward the heater's target temperature when
 * the heater is set to a numeric target.
 */
@Singleton
public class HeatingSimulation {

  public static final String TYPE_TEMPERATURE = "temperature";
  public static final String TYPE_HEATER = "heater";
  public static final String STATE_OFF = "off";

  @Value("${heaters.simulation.outside:10}")
  private double outsideCelsius;

  @Value("${heaters.simulation.step:0.5}")
  private double stepCelsius;

  @Value("${heaters.simulation.enabled:true}")
  private boolean enabled;

  @Inject
  private SensorRepository sensorRepository;

  @Inject
  private ActuatorRepository actuatorRepository;

  @Inject
  private ReadingRepository readingRepository;

  @Inject
  private ReadingsProducer readingsProducer;

  public double getOutsideCelsius() {
    return outsideCelsius;
  }

  public void setOutsideCelsius(double outsideCelsius) {
    this.outsideCelsius = outsideCelsius;
  }

  public double getStepCelsius() {
    return stepCelsius;
  }

  public void setStepCelsius(double stepCelsius) {
    this.stepCelsius = stepCelsius;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Transactional
  @Scheduled(fixedDelay = "${heaters.simulation.rate:1m}", condition = "#{this.enabled}")
  public void simulateHeating() {
    List<Sensor> temperatureSensors = sensorRepository.findByType(TYPE_TEMPERATURE);
    for (Sensor sensor : temperatureSensors) {
      Long roomId = sensor.getRoom().getId();
      List<Actuator> heaters = actuatorRepository.findByRoomId(roomId).stream()
              .filter(a -> TYPE_HEATER.equals(a.getType()))
              .toList();

      Optional<Reading> last = readingRepository.findFirstBySensorIdOrderByTakenAtDesc(sensor.getId());
      double currentTemp = last.map(Reading::getValue).orElse(outsideCelsius);

      double newTemp;
      if (!heaters.isEmpty()) {
        Actuator heater = heaters.get(0);
        boolean isOn = !STATE_OFF.equalsIgnoreCase(heater.getTargetState());
        Optional<Integer> target = parseTarget(heater.getTargetState());
        newTemp = computeNewTemperature(currentTemp, isOn, target);
      } else {
        newTemp = computeNewTemperature(currentTemp, false, Optional.empty());
      }

      Reading reading = new Reading();
      reading.setSensor(sensor);
      reading.setTakenAt(Instant.now());
      reading.setValue(newTemp);
      reading = readingRepository.save(reading);

      ReadingDTO dto = new ReadingDTO();
      dto.setId(reading.getId());
      dto.setSensorId(sensor.getId());
      dto.setSensorName(sensor.getName());
      dto.setTakenAt(reading.getTakenAt());
      dto.setValue(reading.getValue());
      readingsProducer.publishReading(sensor.getId(), dto);
    }
  }

  protected Optional<Integer> parseTarget(String state) {
    try {
      return Optional.of(Integer.parseInt(state.trim()));
    } catch (NumberFormatException ex) {
      return Optional.empty();
    }
  }

  protected double computeNewTemperature(double currentTemp, boolean isOn,
                                         Optional<Integer> targetTemperature) {
    if (isOn && targetTemperature.isPresent()) {
      return Math.min(targetTemperature.get(), currentTemp + stepCelsius);
    } else {
      return Math.max(outsideCelsius, currentTemp - stepCelsius);
    }
  }
}
