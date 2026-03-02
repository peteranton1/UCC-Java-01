package org.example.user;

import org.example.dto.UserAddress;
import org.example.dto.UserRecord;
import org.example.dto.UserSummary;
import org.example.mapper.UserEntityToRecord;
import org.example.mapper.UserRecordToEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


@Service
public class UserService {

    private final UserEntityToRecord toUserRecord = new UserEntityToRecord();
    private final UserRecordToEntity toUserEntity = new UserRecordToEntity();

    private final UserRepository userRepo;

    public UserService(UserRepository repo) {
        this.userRepo = repo;
    }

    public boolean validateAddressApiCall(UserAddress address) throws SocketTimeoutException, ConnectException {

        // this would be a call to a third party service like USPS address validation API

        return true;
    }

    @Transactional
    public UserRecord createUser(UserRecord user) {

        var entity = toUserEntity.apply(user);
        var saved = userRepo.save(entity);
        return toUserRecord.apply(saved);
    }

    @Transactional(readOnly = true)
    public Page<UserSummary> getUserSummaries(Pageable page) {

        return userRepo.loadSummaries(page);
    }
}

