package third;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sensor {
    private SensorType type;
    private SensorState state;
    private String name;
    private String signalMessage;
}
