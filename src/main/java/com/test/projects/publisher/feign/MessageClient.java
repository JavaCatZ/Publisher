package com.test.projects.publisher.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.projects.publisher.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Интерфейс взаимодействия с приложением subscriber
 */
@FeignClient(name = "messageClient", url = "${api.subscriber.url}", configuration = FeignConfiguration.class)
public interface MessageClient {

    /**
     * Запрос на сохранение сообщения
     *
     * @param messageNode JSON-объект отправляемого сообщения
     * @return идентификатор сохранённого сообщения
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/messages", consumes = "application/json")
    ResponseEntity<Long> sendMessage(@RequestBody JsonNode messageNode);
}
