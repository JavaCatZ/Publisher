package com.test.projects.publisher.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.projects.publisher.feign.MessageClient;
import com.test.projects.publisher.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Поток отправки сообщения
 */
@Slf4j
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class MessageThread implements Runnable {

    private final MessageClient messageClient;

    /**
     * Получает сформированный JSON-объект сообщения и отправляет его
     */
    @Override
    public void run() {
        boolean errorNone = false;
        while (!errorNone) {
            try {
                JsonNode node = MessageUtils.getMessage();
                log.info(String.format("Отправка сообщения с id: %s и типом: %s. " +
                        "Поток с id: %d", node.get("id"), node.get("action"), Thread.currentThread().getId()));
                messageClient.sendMessage(node);
                Thread.sleep(15000);
            } catch (Exception ex) {
                log.error(String.format("Ошибка работы потока: %s", ex.getMessage()), ex);
                errorNone = true;
            }
        }
    }
}