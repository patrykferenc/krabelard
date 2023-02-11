package pl.krabelard.krapi.dto;

import lombok.Builder;

@Builder
public record LoginRequest(
        String username,
        String password
) {
}
