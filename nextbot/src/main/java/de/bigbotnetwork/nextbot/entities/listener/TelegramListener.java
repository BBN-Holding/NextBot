package de.bigbotnetwork.nextbot.entities.listener;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramListener {

    void onUpdate(Update update);

}
