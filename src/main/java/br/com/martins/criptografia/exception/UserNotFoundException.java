package br.com.martins.criptografia.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuário não encontrado, para esse id: " + id);
    }
}
