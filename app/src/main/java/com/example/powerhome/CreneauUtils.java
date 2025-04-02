
package com.example.powerhome;

import java.util.*;

public class CreneauUtils {

    public static List<String> getCreneaux() {
        return Arrays.asList("06:00", "08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00");
    }

    public static String getCreneauForHour(String heure) {
        int h = Integer.parseInt(heure.substring(0, 2));
        if (h < 8) return "06:00";
        if (h < 10) return "08:00";
        if (h < 12) return "10:00";
        if (h < 14) return "12:00";
        if (h < 16) return "14:00";
        if (h < 18) return "16:00";
        if (h < 20) return "18:00";
        if (h < 22) return "20:00";
        return "22:00";
    }
}
