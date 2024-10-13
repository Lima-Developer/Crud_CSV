package Domain.Interfaces;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public interface Conversor {

    private static byte[] convertStringToFixedBytes(String input, int tamanho) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > tamanho) {
            // Trunca se o comprimento for maior que o tamanho desejado
            return new String(bytes, 0, tamanho, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8);
        } else {
            // Se for menor ou igual ao tamanho desejado, retorna como está
            return bytes;
        }
    }

    private static byte[] convertIntToFixedBytes(int valor, int tamanho) {
        ByteBuffer buffer = ByteBuffer.allocate(tamanho);
        buffer.putInt(valor);
        return buffer.array();  // Retorna os 4 bytes do inteiro
    }

    static byte[] intoBytes(Object input, int tamanho) {
        byte[] result;
        if (input instanceof String) {
            result = convertStringToFixedBytes((String) input, tamanho);
        } else {
            result = convertIntToFixedBytes((int) input, tamanho);
        }
        return result;
    }
}
