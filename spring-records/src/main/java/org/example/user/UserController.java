package org.example.user;

import jakarta.validation.Valid;
import org.example.config.MySpecialConfig;
import org.example.dto.UserAddress;
import org.example.dto.UserRecord;
import org.example.dto.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;

@RestController
public class UserController {

    private final UserService userService;
    private final MySpecialConfig config;

    @Autowired
    public UserController(UserService service, MySpecialConfig env) {
        userService = service;
        config = env;
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRecord createUser(@Valid @RequestBody UserRecord newUser) {

        // control nullability at the boundaries and prevent NPE

        return userService.createUser(newUser);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<UserSummary> getUsers(Pageable page) {

        var boundedPage = withMaxSize(page);
        var summaries = userService.getUserSummaries(boundedPage);

        return summaries;
    }


    @RequestMapping(value = "/address/validate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> validateAddress(@RequestBody UserAddress address) {

        return switch (validateAddressWithService(address)) {
            case NetworkResult.Success<Boolean> response -> new ResponseEntity<>(response.result(), HttpStatus.OK);
            case NetworkResult.Failure<Boolean> failure -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            case NetworkResult.Timeout<Boolean> timeout -> new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        };
    }

    private NetworkResult<Boolean> validateAddressWithService(UserAddress address) {

        try {
            return new NetworkResult.Success<>(userService.validateAddressApiCall(address));
        } catch (SocketTimeoutException ste) {
            return new NetworkResult.Timeout<>();
        } catch (Exception ce) {
            return new NetworkResult.Failure<>(ce);
        }
    }


    private Pageable withMaxSize(Pageable page) {
        return page.getPageSize() > config.page().maxSize()
            ? PageRequest.of(page.getPageSize(), config.page().maxSize(), page.getSort())
            : page;
    }
}

