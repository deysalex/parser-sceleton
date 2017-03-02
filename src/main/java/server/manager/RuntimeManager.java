package server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import server.entity.ExportCommand;

import java.io.*;

/**
 * Created by Deys on 15.06.2015.
 */
@Service
public class RuntimeManager {

    private final Logger log = LoggerFactory.getLogger(RuntimeManager.class);

    @Value("${slimerjs.path}")
    private String slimerPath;

    @Value("${slimerjs.script.folder}")
    private String slimerScriptFolder;

    public void run(String command) throws IOException, InterruptedException
    {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        log.info("Command:" + command);

        BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        log.info("Output:  " + outputReader.readLine());

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        log.info("Error:  " + errorReader.readLine());

        process.waitFor();
    }

    public void runSlimerJs(ExportCommand exportCommand) throws IOException, InterruptedException
    {
        StringBuilder sb = new StringBuilder();
        sb.append(slimerPath)
                .append(" ")
                .append(slimerScriptFolder)
                .append(exportCommand.getCommand())
                .append(" \"")
                .append(exportCommand.getExternalUrl())
                .append("\" \"")
                .append(exportCommand.getRequestUrl())
                .append("\" \"")
                .append(exportCommand.getCommandArgs())
                .append("\"");

        run(sb.toString());
    }
}
