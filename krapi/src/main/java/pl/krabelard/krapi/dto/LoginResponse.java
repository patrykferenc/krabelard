package pl.krabelard.krapi.dto;

import lombok.Builder;

@Builder
public record LoginResponse(
        String uuid,
        String jwt
) {
}
