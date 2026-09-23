package com.metaminds.kubelab.responses;

public record ErrorResponse(
        int errorCode,
        String errorType,
        String message
) {
}
