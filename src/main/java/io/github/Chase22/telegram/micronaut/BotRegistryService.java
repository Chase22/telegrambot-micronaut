package io.github.Chase22.telegram.micronaut;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceStartedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.WebhookBot;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BotRegistryService implements ApplicationEventListener<ServiceStartedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(BotRegistryService.class.getName());

    @Inject
    List<LongPollingBot> longPollingBots;

    @Inject
    List<WebhookBot> webhookBots;

    public void onApplicationEvent(final ServiceStartedEvent event) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        logger.info("Registering Longpolling Bots");
        for (LongPollingBot bot : longPollingBots) {
            try {
                telegramBotsApi.registerBot(bot);
                logger.info("Registered {}", bot.getBotUsername());
            } catch (TelegramApiRequestException e) {
                logger.error(e.getMessage(), e);
            }
        }

        logger.info("Registering Webhook Bots");
        for (WebhookBot bot : webhookBots) {
            try {
                telegramBotsApi.registerBot(bot);
                logger.info("Registered {}", bot.getBotUsername());
            } catch (TelegramApiRequestException e) {
                logger.error(e.getMessage(), e);
            }
        }
        logger.info("Finished registering bots");
    }
}
