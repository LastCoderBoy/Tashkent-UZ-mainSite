package util;

import enums.UzMonth;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateUtils {

    private static final DateTimeFormatter CARD_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4})");

    private DateUtils() {
    }

    public static LocalDate extractDateFromText(String text) {
        Matcher matcher = DATE_PATTERN.matcher(text);
        if (!matcher.find()) {
            throw new IllegalStateException("Could not parse date from text: " + text);
        }
        return LocalDate.parse(matcher.group(1), CARD_DATE_FORMATTER);
    }

    public static YearMonth parseUzMonthYearLabel(String monthLabel) {
        String[] parts = monthLabel.trim().split("\\s+");
        if (parts.length != 2) {
            throw new IllegalStateException("Unexpected month label format: " + monthLabel);
        }

        UzMonth uzMonth = UzMonth.fromUzName(parts[0]);
        int year = Integer.parseInt(parts[1]);
        return YearMonth.of(year, uzMonth.getNumber());
    }
}
