package third;

import lombok.*;

@Getter
@AllArgsConstructor
public enum SensorState {
    FLASHING("Мигающий"),
    SILENT("Молчащий"),
    BROKEN("Сломанный");

    private final String name;
}
