package med.voll.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class TratadorDeErros {

    private final View error;

    public TratadorDeErros(View error) {
        this.error = error;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var error = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DadosValidacao::new));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Verifica se a mensagem de erro contém informação sobre a duplicação
        if (ex.getMessage().contains("Duplicate entry")) {
            // Extrai o campo que causou a violação de unicidade
            String campoDuplicado = extrairCampoDuplicado(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O valor informado para o campo '" + campoDuplicado + "' já está em uso. Por favor, utilize um valor diferente.");
        }
        // Se for outro tipo de erro de integridade, retorne uma mensagem genérica
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro de integridade de dados: " + ex.getMessage());
    }

    // Método para extrair o campo que causou a duplicação
    private String extrairCampoDuplicado(String mensagemErro) {
        // Exemplo de mensagem de erro: "Duplicate entry 'claudia.ferreira@voll.med' for key 'medicos.email'"
        String[] partes = mensagemErro.split("for key '");
        if (partes.length > 1) {
            String chave = partes[1].split("'")[0];
            // Remove o prefixo 'medicos.' se presente
            if (chave.startsWith("medicos.")) {
                chave = chave.substring("medicos.".length());
            }
            return chave;
        }
        return "desconhecido";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

    public record DadosValidacao(String campo, String mensagem) {
        public DadosValidacao(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
