package third;

import lombok.*;

@AllArgsConstructor
public enum SensorState {
    FLASHING("Мигающий"),
    SILENT("Молчащий"),
    BROKEN("Сломанный");

    @Getter
    private final String name;
}
