package third;

import lombok.*;

@AllArgsConstructor
public enum SensorType {
    SUB_ETHEREAL("Суб-эфирный"),
    GLOSSY("Глянцевый"),
    PETROLEUM("Нефтяной");

    @Getter
    private final String name;
}
