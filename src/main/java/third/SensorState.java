package third;

import lombok.*;

@Getter
@AllArgsConstructor
public enum SensorState {
    FLASHING("Мигающий"),
    SILENT("Молчащий"),
    BROKEN("Сломанный"),
    DISCHARGED("Разряженный");

    private final String name;
}
