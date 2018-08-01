package de.bigbotnetwork.nextbot.listener.telegram;

import de.bigbotnetwork.nextbot.entities.listener.TelegramListener;
import org.telegram.telegrambots.meta.api.objects.Update;

public class test implements TelegramListener {
    @Override
    public void onUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println("Telegram Message "+update.getMessage().getText());
        }
    }
}
