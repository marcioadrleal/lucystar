package br.com.lucystar.login.dto.login;

import java.util.List;

public record WebTokenDto(String name , String subject , List<String> roles ) {

}
