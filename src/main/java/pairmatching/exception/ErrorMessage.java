package pairmatching.exception;

public enum ErrorMessage {

    EMPTY_INPUT("입력값이 비어있습니다."),
    INVALID_NUMBER_FORMAT("숫자 형식이 올바르지 않습니다."),
    INVALID_SEPARATION_COUNT("과정, 레벨, 미션의 3개의 데이터가 입력되야 합니다."),

    FILE_NOT_FOUND("파일을 찾을 수 없습니다."),
    FILE_READ_FAILED("파일을 읽을 수 없습니다."),
    EMPTY_CREW_DATA("해당 타입의 개발자 데이터가 비어있습니다."),

    INVALID_FEATURE_MENU("존재하지 않는 메뉴 입니다."),
    INVALID_DEVELOP_TYPE("개발자 종류는 프론트엔트 또는 벡앤드여야 합니다."),
    INVALID_LEVEL("존재하지 않는 레벨입니다."),
    INVALID_MISSION("존재하지 않는 미션입니다."),
    INVALID_CREW("존재하지 않는 크루입니다."),
    INVALID_REMATCH_STATUS("존재하지 않는 재매치 상태 입니다."),
    NOT_EXIST_PAIR_MATCH_RECORD("매칭 이력이 없습니다."),

    INVALID_CREW_COUNT("페어 매칭을 위해서는 최소 2명은 크루가 필요합니다."),
    PAIR_MATCHING_FAILURE("페어 매칭에 실패 했습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
