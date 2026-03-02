package org.example;

import org.example.config.MySpecialConfig;
import org.example.dto.UserRecord;
import org.example.user.UserController;
import org.example.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// almost of the full stack is used, and your code will be called in exactly the same way
// as if it were processing a real HTTP request but without the cost of starting the server
// WebMvcTest (as opposed to pure unit test) is good for testing:
//   HTTP request mapping
//   Input field validation
//   Serialization / Deserialization
//   Error handling

@WebMvcTest(UserController.class)
public class UserControllerWebMvcTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ObjectWriter jsonWriter = mapper.writerFor(UserRecord.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    // Records are always final, so a record cannot be a @MockBean or be mocked at all
    // Resolutions:
    // - use the Mockito extension that allows mocking final classes (org.mockito:mockito-inline)
    // - use a record that implements an interface, and the interface can be mocked
    @MockitoBean
    MySpecialConfig config;


    @Test
    public void testRegistrationFailsSizeValidation() throws Exception {

        UserRecord badUser = new UserRecord("1", "x");

        var reqBuilder = post("/user")
            .content(jsonWriter.writeValueAsString(badUser))
            .contentType(MediaType.APPLICATION_JSON);

        // this tests that the validation is applied
        mockMvc.perform(reqBuilder)
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void testRegistrationPassesValidation() throws Exception {

        UserRecord goodUser = new UserRecord("12345", "x@123.com");

        var reqBuilder = post("/user")
            .content(jsonWriter.writeValueAsString(goodUser))
            .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(reqBuilder)
            .andDo(print())
            .andExpect(status().isOk());
    }

}