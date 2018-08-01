package de.bigbotnetwork.nextbot.entities;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public class TelegramBot extends TelegramLongPollingBot {

    private Bot bot;

    public TelegramBot(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onUpdateReceived(Update update) {
        bot.getTelegramListeners().forEach(telegramListener -> telegramListener.onUpdate(update));
    }

    @Override
    public String getBotUsername() {
        return bot.getConfig().getBotName();
    }

    @Override
    public String getBotToken() {
        return bot.getConfig().getTelegramToken();
    }
}
