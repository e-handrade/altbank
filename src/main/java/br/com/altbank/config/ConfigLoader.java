package br.com.altbank.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {
    public static String getEnv(String key) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        return dotenv.get(key);
    }
}