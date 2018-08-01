package de.bigbotnetwork.nextbot.commands;

import de.bigbotnetwork.nextbot.entities.commands.Command;
import de.bigbotnetwork.nextbot.entities.commands.Executor;

public class testCommand implements Command {

    @Override
    public void onCommand(String[] args, Executor executor) {
        executor.execute(
                event -> {
                    System.out.println("Discord Befehl by "+event.getAuthor().getName());
                },
                update -> {
                    System.out.println("Telegram Befehl by "+update.getMessage().getFrom().getUserName());
                }
        );
    }
}
