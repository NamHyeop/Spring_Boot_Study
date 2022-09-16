package connected.communication.exception;

/**
 * packageName    : connected.communication.exception
 * fileName       : CannotConvertNestedStructureException
 * author         : namhyeop
 * date           : 2022/09/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/05        namhyeop       최초 생성
 */
public class CannotConvertNestedStructureException extends RuntimeException{
    public CannotConvertNestedStructureException() {
    }

    public CannotConvertNestedStructureException(String message) {
        super(message);
    }

    public CannotConvertNestedStructureException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotConvertNestedStructureException(Throwable cause) {
        super(cause);
    }
}
