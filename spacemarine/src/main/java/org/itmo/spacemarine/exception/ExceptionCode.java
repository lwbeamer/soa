package org.itmo.spacemarine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    // 4xx
    InvalidRequest(HttpStatus.BAD_REQUEST),

    // 401
    TokenIsInactiveException(HttpStatus.UNAUTHORIZED),

    // 404
    SpaceMarineNotFound(HttpStatus.NOT_FOUND),
    StarshipNotFound(HttpStatus.NOT_FOUND),

    // 403
    AccessForbidden(HttpStatus.FORBIDDEN),
    CompanyIdMissMathException(HttpStatus.FORBIDDEN),

    // 412
    FeatureIsDisabled(HttpStatus.PRECONDITION_FAILED),
    OrganisationMustExists(HttpStatus.PRECONDITION_FAILED),
    OperationCompanyIdMissMatch(HttpStatus.PRECONDITION_FAILED),
    OperationNotMeetRecommendations(HttpStatus.PRECONDITION_FAILED),

    // 422
    FeedbackIsHidden(HttpStatus.UNPROCESSABLE_ENTITY),
    FeedbackAlreadyExist(HttpStatus.UNPROCESSABLE_ENTITY),
    ReplyAlreadyExist(HttpStatus.UNPROCESSABLE_ENTITY),
    FeedbackIsNotDeclined(HttpStatus.UNPROCESSABLE_ENTITY),
    FeedbackAlreadyDeclined(HttpStatus.UNPROCESSABLE_ENTITY),
    TextPremoderationFailed(HttpStatus.UNPROCESSABLE_ENTITY),
    ReviewerAndCounterpartyAreTheSame(HttpStatus.UNPROCESSABLE_ENTITY),

    // 5xx,
    ClientRequestFailed(HttpStatus.SERVICE_UNAVAILABLE),
    UnexpectedInternalError(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

}
