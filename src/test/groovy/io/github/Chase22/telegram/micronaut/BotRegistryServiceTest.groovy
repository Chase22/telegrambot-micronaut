package io.github.Chase22.telegram.micronaut;

import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

import static java.util.Objects.nonNull


@MicronautTest
class BotRegistryServiceSpec extends Specification {
    @Inject
    BotRegistryService botRegistryService

    def "BotRegistryService should get injected"() {
        expect:
        nonNull(botRegistryService)
    }
}