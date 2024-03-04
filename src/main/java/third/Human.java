package third;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Human {

    private boolean sadnessInTheEyes;
    private boolean cottonInTheEars;
    private Sensor sensor;
    private Planet planet;

    public String stayAndLookAtTheSky() {
        if (sadnessInTheEyes && cottonInTheEars) {
            return "Лишь один человек стоял и смотрел в небо с грустью в глазах и с ватой в ушах";
        }
        throw new RuntimeException("Нельзя смотреть в небо без грусти в глазах и/или ваты в ушах!");
    }

    public boolean knowsWhatsGoingOn() {
        if (sensor == null) {
            throw new RuntimeException("У человека нет сенсора!");
        }
        return SensorType.SUB_ETHEREAL == sensor.getType()
                && SensorState.FLASHING == sensor.getState();
    }

    public String getReadableSignalFromSensor() {
        var signalMessage = sensor.getSignalMessage();
        if (signalMessage == null) {
            throw new RuntimeException("Не удалось получить сигнал сенсора!");
        }
        return Base64Encoder.decodeFromBase64(signalMessage);
    }
}
