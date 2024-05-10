package org.example.sandship.persistence.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PlayerRequest {

    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "Nickname can't contains spaces.")
    private String nickname;

    public String getNickname() {
        return nickname;
    }
}
