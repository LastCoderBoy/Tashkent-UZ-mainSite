package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UzMonth {
    YANVAR("yanvar", 1),
    FEVRAL("fevral", 2),
    MART("mart", 3),
    APREL("aprel", 4),
    MAY("may", 5),
    IYUN("iyun", 6),
    IYUL("iyul", 7),
    AVGUST("avgust", 8),
    SENTYABR("sentyabr", 9),
    OKTYABR("oktyabr", 10),
    NOYABR("noyabr", 11),
    DEKABR("dekabr", 12);

    private final String uzName;
    private final int number;

    public static UzMonth fromUzName(String name) {
        return Arrays.stream(values())
                .filter(m -> m.uzName.equals(name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown month: " + name));
    }
}
