package durgaproject.jornalapp1.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OpenAIChatService {

    @Autowired
    private WebClient openAiWebClient;

    public String getChatResponse(String userMessage) {
        Map<String, Object> message = Map.of(
                "role", "user",
                "content", userMessage
        );

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(message),
                "max_tokens", 100,
                "temperature", 0.7
        );

        return openAiWebClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> {
                    if (response.statusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                        System.out.println("Rate limit exceeded");
                    }
                    return response.createException();
                })
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("choices").get(0).get("message").get("content").asText())
                .block();
    }
}

