package dev.amok.DeathNote;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Utils {
    private static final List<Tuple<String, Integer>> intervals = new ArrayList<Tuple<String, Integer>>();
    private static FileConfiguration cfg = Plugin.getInstance().getConfig();

    static {
        intervals.add(new Tuple<String, Integer>(cfg.getString("_translation._other.days"), 86400));
        intervals.add(new Tuple<String, Integer>(cfg.getString("_translation._other.hours"), 3600));
        intervals.add(new Tuple<String, Integer>(cfg.getString("_translation._other.minutes"), 60));
        intervals.add(new Tuple<String, Integer>(cfg.getString("_translation._other.seconds"), 1));
    }

    public static String displayTime(long seconds) {
        List<String> result = new ArrayList<>();

        for (Tuple<String, Integer> interval : intervals) {
            String name = interval.first;
            int count = interval.second;
            long value = seconds / count;
            if (value > 0) {
                seconds -= value * count;
                if (value == 1) {
                    name = name.replaceAll("s$", "");
                }
                result.add(value + name);
            }
        }
        return String.join(" ", result.subList(0, result.size()));
    }
}

class Tuple<X, Y> {
    public final X first;
    public final Y second;

    public Tuple(X first, Y second) {
        this.first = first;
        this.second = second;
    }
}

