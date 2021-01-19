package com.snail.webview.mainprocess;

import android.os.RemoteException;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.snail.common.utils.LogUtil;
import com.snail.webview.IMainProcess2WebProcessInterface;
import com.snail.webview.IWebProcess2MainProcessInterface;
import com.snail.webview.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 命令创建工厂
 */
public class CommandFactory extends IWebProcess2MainProcessInterface.Stub {
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

    @Override
    public void handleWebCommand(String commandName, String params, IMainProcess2WebProcessInterface mainProcess2WebProcess) throws RemoteException {
        if (!TextUtils.isEmpty(commandName) && !TextUtils.isEmpty(params)) {
            Map paramsMap = new Gson().fromJson(params, Map.class);
            Command command = getCommands().get(commandName);
            if (command != null) {
                command.execute(mainProcess2WebProcess, paramsMap);
            }
        }
    }
}
