package com.example.community.controller;

import com.example.community.Provider.GithubProvider;
import com.example.community.dto.AcessTokenDTO;
import com.example.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){
        AcessTokenDTO acessTokenDTO = new AcessTokenDTO();
        acessTokenDTO.setCode(code);
        acessTokenDTO.setRedirect_uri(redirectUri);
        acessTokenDTO.setState(state);
        acessTokenDTO.setClient_id(clientId);
        acessTokenDTO.setClient_secret(clientSecret);
        String acessToken = githubProvider.getAcessToken(acessTokenDTO);
        GithubUser user = githubProvider.getUser(acessToken);
        System.out.println(user.getId());
        return "index";
    }
}
