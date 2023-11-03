package dev.amok.DeathNote.Initializers;

import org.bukkit.event.Listener;

import dev.amok.DeathNote.Plugin;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class EventInit {
    public static void init() {
		try {
			ClassPath cp = ClassPath.from(CommandInit.class.getClassLoader());
			for (ClassInfo classInfo : cp.getTopLevelClassesRecursive("dev.amok.DeathNote.Events")) {
				Class<?> clazz = Class.forName(classInfo.getName());
				try {
					if (Listener.class.isAssignableFrom(clazz)) {
						Listener event = (Listener) clazz.getDeclaredConstructor().newInstance();
						// String eventName = classInfo.getSimpleName().replace("Event", "");
						Plugin.plugin.getServer().getPluginManager().registerEvents(event, Plugin.plugin);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
