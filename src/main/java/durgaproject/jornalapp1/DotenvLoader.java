package durgaproject.jornalapp1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DotenvLoader {
    public static void loadEnv() {
        try {
            Properties props = new Properties();
            props.load(Files.newBufferedReader(Paths.get(".env")));
            props.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

