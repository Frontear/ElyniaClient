package org.frontear.elynia.helper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.frontear.elynia.ElyniaClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {
    private final Logger log;
    private final PrintWriter _writer;

    public Logging(String name) {
        log = LogManager.getLogger(name);
        File log = createLogFile(new File(ElyniaClient.DIR, "logs"), name);
        try {
            _writer = new PrintWriter(log);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to initialize Logging service");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                _writer.close(); // to save the file
            }
        }));
    }

    public String emptyLine() {
        return System.getProperty("line.separator");
    }

    public void info(String s) {
        log.info(s);
        toFile(s, Level.INFO);
    }

    public void warn(String s) {
        log.warn(s);
        toFile(s, Level.WARN);
    }

    public void error(String s) {
        log.error(s);
        toFile(s, Level.ERROR);
    }

    private void toFile(String s, Level level) {
        // todo: find a better way to do this
        _writer.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " +
                "[" + Thread.currentThread().getName() + "/" + level.name() + "]: " + s);
    }

    private File createLogFile(File dir, String name) {
        if (dir.mkdir()) { // was just created, no logs would be present
            return new File(dir, name.toLowerCase() + ".log");
        }
        else { // was not just created, meaning logs are present
            return new File(dir, name.toLowerCase() + "-" + dir.list().length + ".log");
            // dir.list() should never throw an exception, as the directory should always be made thanks to mkdir.
        }
    }
}
