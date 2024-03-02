package third;

import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

@AllArgsConstructor
public class Galaxy {
    private Set<Planet> planets;
    private Set<Race> races;

    public Set<Planet> getPlanetToSayHi() {
        return planets.stream()
                .filter(Planet::isAvailableToReceiveGreeting)
                .collect(Collectors.toSet());
    }

    public Set<Race> getRaceThatCanSayHi() {
        return races.stream()
                .filter(Race::isCanSayHi)
                .collect(Collectors.toSet());
    }
}
