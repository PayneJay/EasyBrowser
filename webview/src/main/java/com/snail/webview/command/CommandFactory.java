package com.snail.webview.command;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 命令创建工厂
 */
public class CommandFactory {
    private static volatile CommandFactory mInstance;
    private final Iterator<Command> mIterator;

    private CommandFactory() {
        ServiceLoader<Command> commands = ServiceLoader.load(Command.class);
        mIterator = commands.iterator();
    }

    public static CommandFactory getInstance() {
        if (null == mInstance) {
            synchronized (CommandFactory.class) {
                if (null == mInstance) {
                    mInstance = new CommandFactory();
                }
            }
        }
        return mInstance;
    }

    public Command getCommand() {
        return mIterator.next();
    }

    public boolean hasNextCommand() {
        return mIterator.hasNext();
    }
}
