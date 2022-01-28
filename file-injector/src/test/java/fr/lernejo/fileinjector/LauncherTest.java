package fr.lernejo.fileinjector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class LauncherTest {

    @Test
    void main() throws IOException {
        Launcher.main(new String[]{"src/test/resources/games.json"});
    }

    @Test
    void emptyMain() throws IOException {
        Launcher.main(new String[]{});
    }
}
