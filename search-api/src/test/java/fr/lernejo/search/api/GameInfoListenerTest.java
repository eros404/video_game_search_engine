package fr.lernejo.search.api;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class GameInfoListenerTest {

    @Test
    void onMessage() {
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            RabbitTemplate rabbitTemplate = springContext.getBean(RabbitTemplate.class);
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            String testGame = """
                {
                        "id": 1,
                        "title": "Dauntless",
                        "thumbnail": "https:\\/\\/www.freetogame.com\\/g\\/1\\/thumbnail.jpg",
                        "short_description": "A free-to-play, co-op action RPG with gameplay similar to Monster Hunter.",
                        "game_url": "https:\\/\\/www.freetogame.com\\/open\\/dauntless",
                        "genre": "MMORPG",
                        "platform": "PC (Windows)",
                        "publisher": "Phoenix Labs",
                        "developer": "Phoenix Labs, Iron Galaxy",
                        "release_date": "2019-05-21",
                        "freetogame_profile_url": "https:\\/\\/www.freetogame.com\\/dauntless"
                    }""";
            rabbitTemplate.convertAndSend("", "game_info", testGame, m -> {
                m.getMessageProperties().getHeaders().put("game_id", "1");
                return m;
            });
        }
    }
}
