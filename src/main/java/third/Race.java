package third;
import lombok.*;

@Getter
@AllArgsConstructor
public class Race {

    private String name;
    private boolean canSayHi;

    public String sayHi(Planet planet) {
        if (canSayHi) {
            return String.format("Привет, %s!", planet.getName());
        }
        throw new RuntimeException("Невозможно сказать 'Привет'!");
    }

}