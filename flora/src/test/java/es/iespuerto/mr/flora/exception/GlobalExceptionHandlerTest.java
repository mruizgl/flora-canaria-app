package es.iespuerto.mr.flora.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {

    @Test
    void resourceNotFoundExceptionReturnsNotFoundResponse() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Request description");

        ResponseEntity<?> response = handler.resourceNotFoundException(ex, request);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDetails);
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertEquals("Resource not found", errorDetails.getMessage());
        assertEquals("Request description", errorDetails.getDetails());
    }

    @Test
    void globleExcpetionHandlerReturnsInternalServerErrorResponse() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        Exception ex = new Exception("Internal server error");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Request description");

        ResponseEntity<?> response = handler.globleExcpetionHandler(ex, request);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDetails);
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertEquals("Internal server error", errorDetails.getMessage());
        assertEquals("Request description", errorDetails.getDetails());
    }
}