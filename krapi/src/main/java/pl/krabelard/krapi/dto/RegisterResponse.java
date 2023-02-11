package pl.krabelard.krapi.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RegisterResponse(
        UUID uuid,
        String username,
        String email
) {
}
