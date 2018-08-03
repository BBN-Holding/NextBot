package de.bigbotnetwork.nextbot.util;

import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.commands.Command;
import de.bigbotnetwork.nextbot.entities.commands.Executor;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
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

    public static void sendPhoto(Bot bot, String url, Executor executor) {
        executor.execute(
                event -> {
                    event.getChannel().sendMessage(new EmbedBuilder().setImage(url).build()).queue();
                },
                update -> {
                    try {
                        bot.getTelegramBot().execute(new SendPhoto().setPhoto(url).setChatId(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public static void sendHelp(Bot bot, Command command, Executor executor) {
        executor.execute(
                event -> {
                    event.getChannel().sendMessage(
                            new EmbedBuilder().setTitle(command.labels()[0]).setDescription((command.description()==null) ? "No description provided" : command.description()).build()
                    ).queue();
                },
                update -> {
                    try {
                        bot.getTelegramBot().execute(new SendMessage().setChatId(update.getMessage().getChatId()).setText(command.labels()[0]+"\n"+((command.description()==null) ? "No description provided" : command.description())));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
