package es.iespuerto.mr.flora.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceNotFoundExceptionTest {

    @Test
    void constructorSetsMessageCorrectly() {
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }


}