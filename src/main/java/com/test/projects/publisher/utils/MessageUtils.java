package com.test.projects.publisher.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.projects.publisher.constants.MessageType;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Утилитный класс формирования сообщения
 */
@UtilityClass
public class MessageUtils {

    /**
     * Формирует JSON-объект сообщения
     *
     * @return JSON-объект сообщения
     */
    public JsonNode getMessage() {
        Map<String, Object> jsonFields = new HashMap<>();
        jsonFields.put("id", getId());
        jsonFields.put("msisdn", getMsiSdn());
        jsonFields.put("action", getMessageType());
        jsonFields.put("timestamp", getTimeMillis());

        return new ObjectMapper().convertValue(jsonFields, JsonNode.class);
    }

    /**
     * Возвращает сгенерированный идентификатор сообщения
     *
     * @return идентификатор сообщения
     */
    private Long getId() {
        return Math.abs(new Random().nextLong());
    }

    /**
     * Возвращает сгенерированный уникальный номер абонента
     *
     * @return номер абонента
     */
    private Long getMsiSdn() {
        return Math.abs(new Random().nextLong());
    }

    /**
     * Возвращает случайный тип сообщения
     *
     * @return тип сообщения
     */
    private MessageType getMessageType() {
        int index = new Random().nextInt(MessageType.values().length);
        return List.of(MessageType.values()).get(index);
    }

    /**
     * Возвращает время в миллисекундах
     *
     * @return время в миллисекундах
     */
    private Long getTimeMillis() {
        return System.currentTimeMillis();
    }
}