package br.com.lucystar.login.dto.login;

public record TokenDto(String token, String refreshToken, String generatedAt) {

}
