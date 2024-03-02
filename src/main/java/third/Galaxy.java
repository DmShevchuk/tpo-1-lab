package third;

import java.util.*;
import lombok.*;

@AllArgsConstructor
public class Galaxy {
    private Set<Planet> planets;
    private Set<Race> races;

    public Set<Planet> getPlanetToSayHi() {
        return planets.stream()
                .filter(Planet::isAvailableToReceiveGreeting)
                .collect(java.util.stream.Collectors.toSet());
    }

    public Set<Race> getRaceThatCanSayHi() {
        return races.stream()
                .filter(Race::isCanSayHi)
                .collect(java.util.stream.Collectors.toSet());
    }
}
