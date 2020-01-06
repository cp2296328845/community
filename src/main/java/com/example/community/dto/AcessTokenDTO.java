package com.example.community.dto;

import lombok.Data;

@Data
public class AcessTokenDTO {
    private String client_id;
    private String client_secret;
    public String code;
    public String redirect_uri;
    public String state;

}
