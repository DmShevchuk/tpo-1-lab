package third;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.codec.binary.Base64;


public class TextTest {

    @Nested
    class Base64EncoderTest {

        @Test
        @DisplayName("")
        void f() {
            String encodedString = java.util.Base64.getEncoder().encodeToString("aaaaaaaaa".getBytes());
            assertEquals(encodedString, Base64Encoder.encodeToBase64("aaaaaaaaa"));
        }

        @Test
        @DisplayName("")
        void f1() {
            String originalInput = "test input";
            String encodedString = java.util.Base64.getEncoder().encodeToString(originalInput.getBytes());
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(encodedString);
            String decodedString = new String(decodedBytes);
            assertEquals(decodedString, Base64Encoder.decodeFromBase64(encodedString));
        }

        @Test
        @DisplayName("")
        void f2() {
            assertThrows(NullPointerException.class, () -> Base64Encoder.decodeFromBase64(null));
        }

        @Test
        @DisplayName("")
        void f3() {
            assertThrows(NullPointerException.class, () -> Base64Encoder.encodeToBase64(null));
        }
    }
}
