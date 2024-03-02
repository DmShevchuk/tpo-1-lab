package third;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.apache.commons.codec.binary.Base64;
import java.util.*;

public class TextTest {

    @Nested
    class Base64EncoderTest {

        @ParameterizedTest
        @ValueSource(
                strings = {
                        "Закодируй меня",
                        "Encode me",
                        "Длинная строка с цифрами от 1 до 9",
                        ""
                }
        )
        void correctEncode(String value) {
            byte[] bytesEncoded = Base64.encodeBase64(value.getBytes());
            assertEquals(new String(bytesEncoded), Base64Encoder.encodeToBase64(value));
        }

        @ParameterizedTest
        @ValueSource(
                strings = {
                        "0KHRgtGA0L7QutCwINC40Lcg0YDRg9GB0YHQutC40YUg0YHQuNC80LLQvtC70L7Qsg==",
                        "ZW5nbGlzaA==",
                        "0KHRgtGA0L7QutCwINGBINGG0LjRhNGA0LDQvNC4INC+0YIgMSDQtNC+IDk=",
                        ""
                }
        )
        void correctDecode(String encodedString) {
            byte[] valueDecoded = Base64.decodeBase64(encodedString.getBytes());
            assertEquals(new String(valueDecoded), Base64Encoder.decodeFromBase64(encodedString));
        }

        @Test
        @DisplayName("Кодирование null")
        void encodeNullThrowsNpe() {
            assertThrows(NullPointerException.class, () -> Base64Encoder.decodeFromBase64(null));
        }

        @Test
        @DisplayName("Декодирование null")
        void decodeNullThrowsNpe() {
            assertThrows(NullPointerException.class, () -> Base64Encoder.encodeToBase64(null));
        }
    }

    @Nested
    class SensorTypeTest {

        @ParameterizedTest
        @EnumSource(SensorType.class)
        void testSensorTypeNames(SensorType sensorType) {
            switch (sensorType) {
                case SUB_ETHEREAL -> assertEquals("Суб-эфирный", sensorType.getName());
                case GLOSSY -> assertEquals("Глянцевый", sensorType.getName());
                case PETROLEUM -> assertEquals("Нефтяной", sensorType.getName());
                default -> throw new AssertionError("Неизвестный тип сенсора: " + sensorType);
            }
        }

        @ParameterizedTest
        @ValueSource(ints = {3})
        void testSensorTypeValues(int enumValuesSize) {
            assertEquals(enumValuesSize, SensorType.values().length);
        }
    }

    @Nested
    class SensorStateTest {

        @ParameterizedTest
        @EnumSource(SensorState.class)
        void testSensorStateNames(SensorState sensorState) {
            switch (sensorState) {
                case FLASHING -> assertEquals("Мигающий", sensorState.getName());
                case SILENT -> assertEquals("Молчащий", sensorState.getName());
                case BROKEN -> assertEquals("Сломанный", sensorState.getName());
                case DISCHARGED -> assertEquals("Разряженный", sensorState.getName());
                default -> throw new AssertionError("Неизвестное состояние сенсора: " + sensorState);
            }
        }

        @ParameterizedTest
        @ValueSource(ints = {4})
        void testSensorTypeValues(int enumValuesSize) {
            assertEquals(enumValuesSize, SensorState.values().length);
        }
    }

    @Nested
    class SensorTest {

        @ParameterizedTest
        @EnumSource(SensorType.class)
        void testSensorType(SensorType sensorType) {
            Sensor sensor = new Sensor(
                    sensorType,
                    SensorState.FLASHING,
                    "Sensor1",
                    Base64Encoder.encodeToBase64("Signal")
            );
            assertEquals(sensorType, sensor.getType());
        }

        @ParameterizedTest
        @EnumSource(SensorState.class)
        void testSensorState(SensorState sensorState) {
            Sensor sensor = new Sensor(
                    SensorType.GLOSSY,
                    sensorState,
                    "Sensor2",
                    Base64Encoder.encodeToBase64("Signal")
            );
            assertEquals(sensorState, sensor.getState());
        }

        @ParameterizedTest
        @ValueSource(strings = {"Sensor3", "Sensor4", "Sensor5"})
        void testSensorName(String name) {
            Sensor sensor = new Sensor(
                    SensorType.PETROLEUM,
                    SensorState.BROKEN,
                    name,
                    Base64Encoder.encodeToBase64("Signal")
            );
            assertEquals(name, sensor.getName());
        }

        @ParameterizedTest
        @ValueSource(strings = {"Signal1", "Signal2", "Signal3"})
        void testSensorSignalMessage(String signalMessage) {
            Sensor sensor = new Sensor(
                    SensorType.SUB_ETHEREAL,
                    SensorState.DISCHARGED,
                    "Sensor6",
                    Base64Encoder.encodeToBase64(signalMessage)
            );
            assertEquals(Base64Encoder.encodeToBase64(signalMessage), sensor.getSignalMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"Signal1", "Signal2", "Signal3"})
        void testSensorAllArgsConstructor(String signalMessage) {
            var encodedSignalMessage = Base64Encoder.encodeToBase64(signalMessage);
            Sensor sensor = new Sensor(
                    SensorType.GLOSSY,
                    SensorState.SILENT,
                    "Sensor7",
                    encodedSignalMessage
            );
            assertEquals(SensorType.GLOSSY, sensor.getType());
            assertEquals(SensorState.SILENT, sensor.getState());
            assertEquals("Sensor7", sensor.getName());
            assertEquals(encodedSignalMessage, sensor.getSignalMessage());
        }

        @Test
        void testNoArgsConstructor() {
            Sensor sensor = new Sensor();
            assertNull(sensor.getType());
            assertNull(sensor.getState());
            assertNull(sensor.getName());
            assertNull(sensor.getSignalMessage());
        }


        @ParameterizedTest
        @EnumSource(SensorType.class)
        void testSensorTypeSetter(SensorType sensorType) {
            Sensor sensor = new Sensor(
                    SensorType.GLOSSY,
                    SensorState.FLASHING,
                    "Sensor1",
                    Base64Encoder.encodeToBase64("Signal")
            );
            sensor.setType(sensorType);
            assertEquals(sensorType, sensor.getType());
        }

        @ParameterizedTest
        @EnumSource(SensorState.class)
        void testSensorStateSetter(SensorState sensorState) {
            Sensor sensor = new Sensor(
                    SensorType.GLOSSY,
                    SensorState.DISCHARGED,
                    "Sensor2",
                    Base64Encoder.encodeToBase64("Signal")
            );
            sensor.setState(sensorState);
            assertEquals(sensorState, sensor.getState());
        }

        @ParameterizedTest
        @ValueSource(strings = {"Sensor3", "Sensor4", "Sensor5"})
        void testSensorNameSetter(String name) {
            Sensor sensor = new Sensor(
                    SensorType.PETROLEUM,
                    SensorState.BROKEN,
                    "Name",
                    Base64Encoder.encodeToBase64("Signal")
            );
            sensor.setName(name);
            assertEquals(name, sensor.getName());
        }

        @ParameterizedTest
        @ValueSource(strings = {"Signal1", "Signal2", "Signal3"})
        void testSensorSignalMessageSetter(String signalMessage) {
            Sensor sensor = new Sensor(
                    SensorType.SUB_ETHEREAL,
                    SensorState.DISCHARGED,
                    "Sensor6",
                    Base64Encoder.encodeToBase64("Случайная строка")
            );
            sensor.setSignalMessage(Base64Encoder.encodeToBase64(signalMessage));
            assertEquals(Base64Encoder.encodeToBase64(signalMessage), sensor.getSignalMessage());
        }
    }

    @Nested
    class RaceTest {

        @ParameterizedTest
        @CsvSource({"Humans, true", "Aliens, false"})
        void testGetterAndAllArgsConstructor(String name, boolean canSayHi) {
            Race race = new Race(name, canSayHi);
            assertEquals(name, race.getName());
            assertEquals(canSayHi, race.isCanSayHi());
        }

        @ParameterizedTest
        @CsvSource({"Земля, true, Vogons, true", "Марс, true, Mans, true", "Венера, true, Aliens, true"})
        void testSayHi(String planetName, boolean canSayHi, String raceName, boolean canRaceSayHi) {
            Race race = new Race(raceName, canRaceSayHi);
            Planet planet = new Planet(planetName, canSayHi);
            assertEquals(String.format("Привет, %s!", planet.getName()), race.sayHi(planet));
        }

        @ParameterizedTest
        @CsvSource({"Земля, false", "Марс, false", "Венера, false"})
        void testSayHiThrowsException(String planetName, boolean canSayHi) {
            Race race = new Race("Humans", false);
            Planet planet = new Planet(planetName, canSayHi);
            assertThrows(RuntimeException.class, () -> race.sayHi(planet));
        }
    }

    @Nested
    class PlanetTest {
        @ParameterizedTest
        @CsvSource({"Earth, true", "Mars, false", "Jupiter, true"})
        void testGetName(String name, boolean availableToReceiveGreeting) {
            Planet planet = new Planet(name, availableToReceiveGreeting);
            assertEquals(name, planet.getName());
        }

        @ParameterizedTest
        @CsvSource({"Earth, true", "Mars, false", "Jupiter, true"})
        void testIsAvailableToReceiveGreeting(String name, boolean availableToReceiveGreeting) {
            Planet planet = new Planet(name, availableToReceiveGreeting);
            assertEquals(availableToReceiveGreeting, planet.isAvailableToReceiveGreeting());
        }

        @ParameterizedTest
        @CsvSource({"Saturn, false", "Neptune, true", "Uranus, false"})
        void testAllArgsConstructor(String name, boolean availableToReceiveGreeting) {
            Planet planet = new Planet(name, availableToReceiveGreeting);
            assertEquals(name, planet.getName());
            assertEquals(availableToReceiveGreeting, planet.isAvailableToReceiveGreeting());
        }

        @Test
        void testNoArgsConstructor() {
            Planet planet = new Planet();
            assertNull(planet.getName());
        }
    }

    @Nested
    class HumanTest {

        Sensor buildAndGetDefaultSensor(SensorState state) {
            return Sensor.builder()
                    .type(SensorType.SUB_ETHEREAL)
                    .state(state)
                    .name("sensor")
                    .signalMessage("signal")
                    .build();
        }

        @ParameterizedTest
        @EnumSource(value = SensorState.class)
        void stayAndLookAtTheSkyWithCottonAndSadnessInTheEyes(SensorState state) {
            var human = Human.builder()
                    .sadnessInTheEyes(true)
                    .cottonInTheEars(true)
                    .sensor(buildAndGetDefaultSensor(state))
                    .planet(new Planet())
                    .build();
            assertDoesNotThrow(human::stayAndLookAtTheSky);
        }

        @ParameterizedTest
        @EnumSource(value = SensorState.class, names = {"FLASHING"})
        void knowsWhatsGoingOnWithValidSensorState(SensorState state) {
            var human = Human.builder()
                    .sadnessInTheEyes(true)
                    .cottonInTheEars(true)
                    .sensor(buildAndGetDefaultSensor(state))
                    .planet(new Planet())
                    .build();
            assertTrue(human.knowsWhatsGoingOn());
        }

        @ParameterizedTest
        @EnumSource(value = SensorState.class, names = {"BROKEN", "DISCHARGED"})
        void knowsWhatsGoingOnWithInvalidSensorState(SensorState state) {
            var human = Human.builder()
                    .sadnessInTheEyes(true)
                    .cottonInTheEars(true)
                    .sensor(buildAndGetDefaultSensor(state))
                    .planet(new Planet())
                    .build();
            assertFalse(human.knowsWhatsGoingOn());
        }

        @Test
        void getReadableSignalFromSensor_withValidSignalMessage() {
            var sensor = buildAndGetDefaultSensor(SensorState.FLASHING);
            sensor.setSignalMessage(Base64Encoder.encodeToBase64("decoded_signal"));
            var human = Human.builder()
                    .sadnessInTheEyes(true)
                    .cottonInTheEars(true)
                    .sensor(sensor)
                    .planet(new Planet())
                    .build();
            assertEquals("decoded_signal", human.getReadableSignalFromSensor());
        }

        @Test
        void stayAndLookAtTheSkyWithoutCottonOrSadnessInTheEyes() {
            var human = Human.builder()
                    .sadnessInTheEyes(false)
                    .cottonInTheEars(true)
                    .sensor(buildAndGetDefaultSensor(SensorState.FLASHING))
                    .planet(new Planet())
                    .build();
            assertThrows(RuntimeException.class, human::stayAndLookAtTheSky);
        }

        @Test
        void stayAndLookAtTheSkyWithoutSensor() {
            Human human = new Human(true, true, null, null);
            assertDoesNotThrow(human::stayAndLookAtTheSky);
        }

        @Test
        void getReadableSignalFromSensorWithNullSignalMessage() {
            var sensor = buildAndGetDefaultSensor(SensorState.FLASHING);
            sensor.setSignalMessage(null);
            var human = Human.builder()
                    .sadnessInTheEyes(true)
                    .cottonInTheEars(true)
                    .sensor(sensor)
                    .planet(new Planet())
                    .build();
            assertThrows(RuntimeException.class, human::getReadableSignalFromSensor);
        }

        @Test
        void knowsWatsGoingOnWhenSensorIsNull() {
            Human human = new Human(true, true, null, null);
            assertThrows(RuntimeException.class, human::knowsWhatsGoingOn);
        }

        @Test
        void testNoArgsConstructor() {
            Human human = new Human();
            assertThrows(NullPointerException.class, human::getReadableSignalFromSensor);
        }
    }

    @Nested
    class GalaxyTest {
        @Test
        void getPlanetToSayHiWithAvailablePlanets() {
            Set<Planet> planets = new HashSet<>();
            planets.add(new Planet("Earth", true));
            planets.add(new Planet("Mars", false));
            Galaxy galaxy = new Galaxy(planets, new HashSet<>());
            Set<Planet> result = galaxy.getPlanetToSayHi();
            assertEquals(1, result.size());
        }

        @Test
        void getPlanetToSayHiWithNoAvailablePlanets() {
            Set<Planet> planets = new HashSet<>();
            planets.add(new Planet("Mars", false));
            Galaxy galaxy = new Galaxy(planets, new HashSet<>());
            Set<Planet> result = galaxy.getPlanetToSayHi();
            assertEquals(0, result.size());
        }

        @Test
        void getRaceThatCanSayHiWithRacesThatCanSayHi() {
            Set<Race> races = new HashSet<>();
            races.add(new Race("Human", true));
            races.add(new Race("Martian", false));
            Galaxy galaxy = new Galaxy(new HashSet<>(), races);
            Set<Race> result = galaxy.getRaceThatCanSayHi();
            assertEquals(1, result.size());
        }

        @Test
        void getRaceThatCanSayHiWithNoRacesThatCanSayHi() {
            Set<Race> races = new HashSet<>();
            races.add(new Race("Martian", false));
            Galaxy galaxy = new Galaxy(new HashSet<>(), races);
            Set<Race> result = galaxy.getRaceThatCanSayHi();
            assertEquals(0, result.size());
        }

    }

}
