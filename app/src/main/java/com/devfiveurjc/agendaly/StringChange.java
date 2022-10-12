package com.devfiveurjc.agendaly;

import java.util.Locale;

public class StringChange {

    public static String dateString(int[] date) {
        if (date != null && date.length == 3) return String.format(Locale.getDefault(), "%02d", date[0]) + "/" + String.format(Locale.getDefault(), "%02d", date[1] + 1) + "/" + String.format(Locale.getDefault(), "%02d", date[2]);
        return "";
    }
}
