package pl.krabelard.krapi.dto;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String username,
        String email,
        String password
) {
}
