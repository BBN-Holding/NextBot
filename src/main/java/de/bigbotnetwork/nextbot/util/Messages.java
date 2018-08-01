package de.bigbotnetwork.nextbot.util;

import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.commands.Executor;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Messages {

    public static void sendMessage(Bot bot, String text, Executor executor) {
        executor.execute(event -> {
            event.getChannel().sendMessage(text).queue();
        }, update -> {
            try {
                bot.getTelegramBot().execute(new SendMessage().setChatId(update.getMessage().getChatId()).setText(text));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendMessage(Bot bot, String discordtext, String telegramtext, Executor executor) {
        executor.execute(event -> {
            event.getChannel().sendMessage(discordtext).queue();
        }, update -> {
            try {
                bot.getTelegramBot().execute(new SendMessage().setChatId(update.getMessage().getChatId()).setText(telegramtext));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendMessage(Bot bot, MessageEmbed discordtext, String telegramtext, Executor executor) {
        executor.execute(event -> {
            event.getChannel().sendMessage(discordtext).queue();
        }, update -> {
            try {
                bot.getTelegramBot().execute(new SendMessage().setChatId(update.getMessage().getChatId()).setText(telegramtext));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendMessage(Bot bot, EmbedBuilder embedBuilder, String telegramtext, Executor executor) {
        sendMessage(bot, embedBuilder.build(), telegramtext, executor);
    }

}
