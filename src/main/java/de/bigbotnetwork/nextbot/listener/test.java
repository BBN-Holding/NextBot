package de.bigbotnetwork.nextbot.listener;

import de.bigbotnetwork.nextbot.entities.listener.TelegramListener;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.telegram.telegrambots.meta.api.objects.Update;

public class test extends ListenerAdapter implements TelegramListener {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

    }

    @Override
    public void onUpdate(Update update) {

    }
}
