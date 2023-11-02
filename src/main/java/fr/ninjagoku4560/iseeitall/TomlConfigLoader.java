package fr.ninjagoku4560.iseeitall;

import com.moandjiezana.toml.Toml;

import java.io.File;

public class TomlConfigLoader {
    public static Toml load() {
        File file = new File("ISIA.toml");
        return new Toml().read(file);
    }
}
