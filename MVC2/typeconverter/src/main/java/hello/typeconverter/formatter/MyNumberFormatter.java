package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {

    /**
     * 자바의 기본적으로 제공하는 NumberFormat 객체를 사용하면 Locale정보를 사용해서
     * 나라별 다른 숫자 포맷을 제공해준다.
     */
    //parse를 사용해서 "문자 -> ,들어간 숫자"로 변환한다.
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text={}, locale={}", text, locale);
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
    }

    //print를 사용해서 "객체->문자" 로 변환한다
    @Override
    public String print(Number object, Locale locale) {
        log.info("object={}, locale={}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
