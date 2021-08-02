package com.test.projects.publisher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Сервис автоматической генерации и отправки сообщений
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final TaskExecutor taskExecutor;
    private final ApplicationContext applicationContext;

    @Value(value = "${api.subscriber.thread-count}")
    private int THREAD_COUNT;

    /**
     * Запускает несколько потоков отправки сообщений в количестве THREAD_COUNT
     */
    @PostConstruct
    public void startMessagesSending() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            log.info(String.format("Запуск %d-го потока", i + 1));
            taskExecutor.execute(applicationContext.getBean(MessageThread.class));
        }
    }
}