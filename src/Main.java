import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("tecnologia.csv", "r")) {
            String line;

            // Ler e ignorar a primeira linha (cabeçalho)
            raf.readLine();  // Ignora a primeira linha

            int numLinhas = 0;
            while ((line = raf.readLine()) != null && numLinhas != 10) {
                String[] values = line.split(",");
                // Chama o método para processar os dados da linha
                intoLineDB(values);
                numLinhas++;
            }
            System.out.println("Total de linhas processadas: " + numLinhas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Função que transforma cada campo em um array de bytes com tamanho fixo ou variável
    public static void intoLineDB(String[] csvLine) throws IOException {
        String nomeTecnologiaOrigem = csvLine[0];
        int tamanhoTecnologiaOrigem = nomeTecnologiaOrigem.length();
        String grupo = csvLine[1];
        String popularidade = csvLine[2];
        String nomeTecnologiaDestino = csvLine[3];
        int tamanhoTecnologiaDestino = nomeTecnologiaDestino.length();
        String peso = csvLine[4];
        String removido = String.valueOf(0);  // Sempre 0, 1 byte

        // Converter cada campo em array de bytes
        byte[] removidoBytes = convertStringToFixedBytes(removido, 1);
        byte[] grupoBytes = convertStringToFixedBytes(grupo, 4);
        byte[] popularidadeBytes = convertStringToFixedBytes(popularidade, 4);
        byte[] pesoBytes = convertStringToFixedBytes(peso, 4);

        // Converter os tamanhos de tecnologia para arrays de bytes com tamanho fixo (4 bytes)
        byte[] tamanhoTecnologiaOrigemBytes = convertIntToFixedBytes(tamanhoTecnologiaOrigem, 4);
        byte[] tamanhoTecnologiaDestinoBytes = convertIntToFixedBytes(tamanhoTecnologiaDestino, 4);

        // Agora, nomeTecnologiaOrigem e nomeTecnologiaDestino têm tamanho variável baseado no tamanhoTecnologiaOrigem e tamanhoTecnologiaDestino
        byte[] nomeTecnologiaOrigemBytes = convertStringToFixedBytes(nomeTecnologiaOrigem, tamanhoTecnologiaOrigem);
        byte[] nomeTecnologiaDestinoBytes = convertStringToFixedBytes(nomeTecnologiaDestino, tamanhoTecnologiaDestino);

        // Exibir corretamente os números inteiros ao invés de interpretá-los diretamente como string
        System.out.println(new String(removidoBytes) + " " +
                new String(grupoBytes) + " " +
                new String(popularidadeBytes) + " " +
                new String(pesoBytes) + " " +
                ByteBuffer.wrap(tamanhoTecnologiaOrigemBytes).getInt() + " " +  // Converte de volta para int
                new String(nomeTecnologiaOrigemBytes) + " " +
                ByteBuffer.wrap(tamanhoTecnologiaDestinoBytes).getInt() + " " +  // Converte de volta para int
                new String(nomeTecnologiaDestinoBytes));
    }

    // Método para converter uma string em um array de bytes de tamanho fixo ou variável
    public static byte[] convertStringToFixedBytes(String input, int tamanho) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > tamanho) {
            // Trunca se o comprimento for maior que o tamanho desejado
            return new String(bytes, 0, tamanho, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8);
        } else {
            // Se for menor ou igual ao tamanho desejado, retorna como está
            return bytes;
        }
    }

    // Método para converter um int em um array de bytes de tamanho fixo (4 bytes)
    public static byte[] convertIntToFixedBytes(int valor, int tamanho) {
        ByteBuffer buffer = ByteBuffer.allocate(tamanho);
        buffer.putInt(valor);
        return buffer.array();  // Retorna os 4 bytes do inteiro
    }
}
