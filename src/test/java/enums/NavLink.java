package enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Maps each navbar item to its URL path. Centralizing this here means:
 * - adding a new nav item = one enum line, not a new By field
 * - tests reference NavLink.NEWS instead of raw strings (compile-safe)
 * - works across all 4 languages since href path stays /uz/... in DOM attribute
 *   even when visible text changes per locale
 */
@Getter
@RequiredArgsConstructor
public enum NavLink {
    DISTRICTS("/uz/districts"),
    NEWS("/uz/news"),
    OPPORTUNITIES("/uz/opportunities"),
    ANTICORRUPTION("/uz/anticorruption"),
    CITY("/uz/city"),
    ABOUT("/uz/about"),
    DEPUTIES("/uz/deputies"),
    SOCIETY("/uz/society");

    private final String path;
}
