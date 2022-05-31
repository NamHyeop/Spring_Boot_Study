package connected.communication.dto;

public class MemberNicknameAlreadyExistsException extends RuntimeException{
    public MemberNicknameAlreadyExistsException(String message) {
        super(message);
    }
}
