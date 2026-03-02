package org.example;

import org.example.config.MySpecialConfig;
import org.example.data.TestData;
import org.example.dto.UserAddress;
import org.example.user.UserController;
import org.example.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerUnitTest {

    private UserController controller;

    private final UserService service = mock(UserService.class);
    private final MySpecialConfig config = new MySpecialConfig(0, new MySpecialConfig.Page(10));

    @BeforeEach
    public void setup() throws Exception {

        controller = new UserController(service, config);
    }


    @Test
    public void testStreams() {

        var words = List.of("here", "is", "a", "word", "list");

        // map the data and pass along with original value

        // using an explicit type during processing makes the same code more readable
        // and we have an explicit type at the end which is easier to reason about
        record ProcessedWord(String word, int length, Instant recorded) { }

        var longWords2 = words.stream()
            .map(word -> new ProcessedWord(word, word.length(), Instant.now()))
            .filter(word -> word.length() > 3)
            .collect(toList());

        System.out.println(longWords2);
    }


    @Test
    public void testAddressValidationHappyPath() throws Exception {

        boolean validity = true;
        when(service.validateAddressApiCall(any(UserAddress.class))).thenReturn(validity);

        var response = controller.validateAddress(TestData.randomAddressRecord());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(validity, response.getBody());
    }

    @Test
    public void testAddressValidationOnTimeout() throws Exception {

        when(service.validateAddressApiCall(any(UserAddress.class))).thenThrow(SocketTimeoutException.class);

        var response = controller.validateAddress(TestData.randomAddressRecord());

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    @Test
    public void testAddressValidationOnException() throws Exception {

        when(service.validateAddressApiCall(any(UserAddress.class))).thenThrow(ConnectException.class);

        var response = controller.validateAddress(TestData.randomAddressRecord());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
