package com.chan.link.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    /* 400 BAD_REQUEST : 잘못된 요청 */
    BAD_INPUT_REQUEST(HttpStatus.BAD_REQUEST,"입력값이 잘못되었습니다."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,  "입력값에 대한 검증을 만족하지 못했습니다."),
    BINDING_ERROR(HttpStatus.BAD_REQUEST, "입력값이 대한 검증을 만족하지 못했습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),
    JWT_AUTHENTICATION_ENTRYPOINT(HttpStatus.UNAUTHORIZED, "유효한 자격증명을 제공하지 않고 접근"),

    /* 403 Forbidden 에러 */
    JWT_ACCESS_DENIED(HttpStatus.FORBIDDEN, "권한이 존재하지 않습니다." ),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */

    /* 405 NOT_ALLOWED  */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,  "HTTP 메서드가 잘못되었습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다"),

    /* 500 error */
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 에러 입니다."),

    //User
    /* 400 error */
    EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "중복된 이메일 입니다."),
    CANNOT_FOLLOW_MYSELF(HttpStatus.BAD_REQUEST, "자기 자신은 팔로우 할 수 없습니다"),

    /* 404 error */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "로그아웃 된 사용자입니다"),
    NOT_FOLLOW(HttpStatus.NOT_FOUND, "팔로우 중이지 않습니다"),

    //POST
    /* 400 error */
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "게시물이 없습니다."),

    /* 404 error */
    POST_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}