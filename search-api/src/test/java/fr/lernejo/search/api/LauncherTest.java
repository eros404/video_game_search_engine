package fr.lernejo.search.api;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class LauncherTest {

    @Test
    void main() {
        assertTimeoutPreemptively(
            Duration.ofSeconds(10L),
            () -> Launcher.main(new String[]{}));
    }
}
