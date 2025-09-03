package br.com.lucystar.login.dto;

import br.com.lucystar.login.enums.StatuEnum;

public record ClientDto( String id , String codeClient , String clientName , StatuEnum status , String localKey ) {

}

