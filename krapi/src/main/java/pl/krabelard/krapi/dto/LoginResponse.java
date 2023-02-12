package pl.krabelard.krapi.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record LoginResponse(
        UUID uuid,
        String jwt
) {
}
