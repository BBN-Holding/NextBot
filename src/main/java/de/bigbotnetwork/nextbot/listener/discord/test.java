package de.bigbotnetwork.nextbot.listener.discord;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class test extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        System.out.println("Discord Message "+event.getMessage().getContentRaw());
    }
}
