package com.snail.webview.command;

import com.snail.common.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 命令创建工厂
 */
public class CommandFactory {
    private static volatile CommandFactory sInstance;
    private final Map<String, Command> mCommands = new HashMap<>();

    private CommandFactory() {
        ServiceLoader<Command> commands = ServiceLoader.load(Command.class);
        LogUtil.d("command : " + commands.iterator().hasNext());
        for (Command command : commands) {
            LogUtil.d("command : " + command);
            if (command != null && !mCommands.containsKey(command.name())) {
                mCommands.put(command.name(), command);
            }
        }
    }

    public static CommandFactory getInstance() {
        if (null == sInstance) {
            synchronized (CommandFactory.class) {
                if (null == sInstance) {
                    sInstance = new CommandFactory();
                    LogUtil.d("mInstance 创建了");
                }
            }
        }
        return sInstance;
    }

    public Map<String, Command> getCommands() {
        return mCommands;
    }
}
