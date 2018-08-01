package de.bigbotnetwork.nextbot.listener;

import de.bigbotnetwork.nextbot.entities.listener.TelegramListener;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.telegram.telegrambots.meta.api.objects.Update;

public class test extends ListenerAdapter implements TelegramListener {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        System.out.println("Discord Message "+event.getMessage().getContentRaw());
    }

    @Override
    public void onUpdate(Update update) {
        System.out.println("Telegram Message "+update.getMessage().getText());
    }
}
