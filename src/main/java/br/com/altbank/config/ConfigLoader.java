package br.com.altbank.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {
    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }
}