package br.com.martins.criptografia.util;

public class GenerateCpfUtil {
    public static String generateCpf() {
        StringBuilder cpf = new StringBuilder();
        for (int i = 1; i <= 11; i++) {
            int num = (int) (Math.random() * 10);
            cpf.append(Integer.toString(num));
        }
        return cpf.toString();
    }
}
