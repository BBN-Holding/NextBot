package de.bigbotnetwork.nextbot.commands;

import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.commands.Command;
import de.bigbotnetwork.nextbot.entities.commands.Executor;
import net.dv8tion.jda.core.EmbedBuilder;

import java.util.Arrays;

import static de.bigbotnetwork.nextbot.util.Messages.sendHelp;
import static de.bigbotnetwork.nextbot.util.Messages.sendMessage;
import static de.bigbotnetwork.nextbot.util.Messages.sendPhoto;

public class BuilderCommand implements Command {

    Bot bot;

    String string;

    public BuilderCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onCommand(String[] args, Executor executor) {
        if (args.length==0) {
            sendHelp(bot, this, executor);
        } else
        switch (args[0].toLowerCase()) {
            case "tombstone":
                executor.execute(
                        event -> {
                            string = event.getMessage().getContentRaw().replaceFirst("next!", "");
                        },
                        update -> {
                            if (update.getMessage().getText().startsWith("/")) {
                                string = update.getMessage().getText().replaceFirst("/", "");
                            } else if (update.getMessage().getText().startsWith("next!")) {
                                string = update.getMessage().getText().replaceFirst("next!", "");
                            }
                        }
                );
                for (String label: labels()) {
                    string = string.replaceFirst(label, "");
                }
                String[] strings = string.replaceFirst(" tombstone ", "").split("~");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("http://www.tombstonebuilder.com/generate.php?");
                for (int i =0; strings.length>i && 4>i; i++) {
                    stringBuilder.append("top"+(i+1)+"=");
                    stringBuilder.append(strings[i].replaceAll(" ", "+"));
                    stringBuilder.append((strings.length-1==i) ? "&sp=" : "&");
                }
                // http://www.tombstonebuilder.com/generate.php?top1=123&top2=223&top3=123123&top4=123123&sp=

                sendPhoto(bot, stringBuilder.toString(), executor);
                break;

            case "road":
                // http://www.customroadsign.com/generate.php?line1=1&line2=2&line3=3&line4=4
                executor.execute(
                        event -> {
                            string = event.getMessage().getContentRaw().replaceFirst("next!", "");
                        },
                        update -> {
                            if (update.getMessage().getText().startsWith("/")) {
                                string = update.getMessage().getText().replaceFirst("/", "");
                            } else if (update.getMessage().getText().startsWith("next!")) {
                                string = update.getMessage().getText().replaceFirst("next!", "");
                            }
                        }
                );
                for (String label: labels()) {
                    string = string.replaceFirst(label, "");
                }
                String[] strings1 = string.replaceFirst(" road ", "").split("~");
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("http://www.customroadsign.com/generate.php?");
                for (int i =0; strings1.length>i && 4>i; i++) {
                    stringBuilder1.append("line"+(i+1)+"=");
                    stringBuilder1.append(strings1[i].replaceAll(" ", "+"));
                    stringBuilder1.append((strings1.length-1==i) ? "" : "&");
                }

                sendPhoto(bot, stringBuilder1.toString(), executor);
                break;

            case "motel":
                //http://www.custommotelsign.com/generate.php?line1=1&line2=2&line3=3&line4=4
                executor.execute(
                        event -> {
                            string = event.getMessage().getContentRaw().replaceFirst("next!", "");
                        },
                        update -> {
                            if (update.getMessage().getText().startsWith("/")) {
                                string = update.getMessage().getText().replaceFirst("/", "");
                            } else if (update.getMessage().getText().startsWith("next!")) {
                                string = update.getMessage().getText().replaceFirst("next!", "");
                            }
                        }
                );
                for (String label: labels()) {
                    string = string.replaceFirst(label, "");
                }
                String[] strings2 = string.replaceFirst(" motel ", "").split("~");
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("http://www.custommotelsign.com/generate.php?");
                for (int i =0; strings2.length>i && 4>i; i++) {
                    stringBuilder2.append("line"+(i+1)+"=");
                    stringBuilder2.append(strings2[i].replaceAll(" ", "+"));
                    stringBuilder2.append((strings2.length-1==i) ? "" : "&");
                }

                sendPhoto(bot, stringBuilder2.toString(), executor);
                break;
        }
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String[] labels() {
        return new String[]{"builder"};
    }
}
