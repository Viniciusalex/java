package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.dto.authenticationDTO.DadosTokenJwtDTO;
import med.voll.api.dto.usuarioDTO.DadosAutenticacaoDTO;
import med.voll.api.infra.security.TokenService;
import med.voll.api.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DadosAutenticacaoDTO dados) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var authenticaon =  authenticationManager.authenticate(authenticationtoken);
        var tokenJWT = tokenService.gerarToken((Usuario) authenticaon.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwtDTO(tokenJWT));
    }





}
