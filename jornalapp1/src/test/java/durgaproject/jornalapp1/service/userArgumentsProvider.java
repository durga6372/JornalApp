package durgaproject.jornalapp1.service;

import durgaproject.jornalapp1.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class userArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
       return Stream.of(
                Arguments.of(User.builder().userName("Danu").password("danu").build()),
       Arguments.of (User.builder().userName("Ketu").password("Ketu").build())
        );
    }
}
