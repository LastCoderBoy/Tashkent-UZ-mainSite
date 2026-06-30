package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchOptions {
    SAYT_BOYLAB("Sayt bo'ylab"),
    XIZMATLAR ("Xizmatlar"),
    IMKONIYATLAR ("Imkoniyatlardan"),
    YANGILIKLAR ("Yangiliklar"),
    HUJJATLAR ("Hujjatlar"),
    AFISHA ("Afisha");

    private final String value;
}
