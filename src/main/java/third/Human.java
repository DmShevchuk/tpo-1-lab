package third;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class Human {

    private boolean sadnessInTheEyes;
    private boolean cottonInTheEars;
    private Sensor sensor;
    private Planet planet;

    void stayAndLookAtTheSky() {
        if (sadnessInTheEyes && cottonInTheEars) {
            System.out.println("Лишь один человек стоял и смотрел в небо с грустью в глазах и с ватой в ушах");
        } else {
            throw new RuntimeException("Нельзя смотреть в небо без грусти в глазах и/или ваты в ушах!");
        }
    }

    boolean knowsWhatsGoingOn() {
        if (sensor == null) {
            throw new RuntimeException("У человека нет сенсора!");
        }
        return SensorType.SUB_ETHEREAL == sensor.getType()
                && SensorState.FLASHING == sensor.getState();
    }

    String getReadableSignalFromSensor() {
        var signalMessage = sensor.getSignalMessage();
        if (signalMessage == null) {
            throw new RuntimeException("Не удалось получить сигнал сенсора!");
        }
        return Base64Encoder.decodeFromBase64(signalMessage);
    }
}
