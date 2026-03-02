package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@ConfigurationProperties(prefix = "my.special.config")
public record MySpecialConfig(int number, @NotNull Page page) {

    public record Page(@Positive int maxSize) {   }

    // application.yaml
    //
    //    my:
    //      special:
    //        config:
    //          number: 99
    //          page:
    //            maxSize: 12

}