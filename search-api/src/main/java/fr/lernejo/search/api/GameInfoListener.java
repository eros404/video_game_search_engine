package fr.lernejo.search.api;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GameInfoListener {
    private final Logger logger = LoggerFactory.getLogger(GameInfoListener.class);
    private final RestHighLevelClient client;
    public  GameInfoListener(RestHighLevelClient client) {
        this.client = client;
    }
    @RabbitListener(queues = AmqpConfiguration.GAME_INFO_QUEUE)
    public void onMessage(String message, @Header("game_id") String gameId) {
        IndexRequest request = new IndexRequest("games")
            .id(gameId)
            .source(message, XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
