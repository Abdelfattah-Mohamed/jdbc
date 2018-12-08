package eg.edu.alexu.csd.oop.jdbc.cs23;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DBLogger {
    private static DBLogger instance;

    public static DBLogger getInstance(){

        if(DBLogger.instance==null){

            DBLogger.instance = new DBLogger();
        }


        return DBLogger.instance;
    }

    public Logger log;

    private FileHandler fh;

    private DBLogger() {
        final File f = new File("log.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (final IOException e) {
                System.out.println("Failed to create log file.");
                e.printStackTrace();
            }
        }
        try {
            fh = new FileHandler("log.txt", true);
        } catch (SecurityException | IOException e) {
            System.out.println("Failed to handle log file.");
            e.printStackTrace();
        } // Appends to log.txt file.
        log = Logger.getLogger("MainLog");
        log.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());
        log.setLevel(Level.INFO);
    }

}
