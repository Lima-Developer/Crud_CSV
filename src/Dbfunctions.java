import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Dbfunctions {
    static Scanner input = new Scanner(System.in);
    
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

        // Tamanho variável
        byte[] nomeTecnologiaOrigemBytes = convertStringToFixedBytes(nomeTecnologiaOrigem, tamanhoTecnologiaOrigem);
        byte[] nomeTecnologiaDestinoBytes = convertStringToFixedBytes(nomeTecnologiaDestino, tamanhoTecnologiaDestino);

        // Exibindo a forma com a qual a linha do banco de dados vai ser apresentada
        showDBLine(removidoBytes, grupoBytes, popularidadeBytes, pesoBytes, tamanhoTecnologiaOrigemBytes, nomeTecnologiaOrigemBytes, tamanhoTecnologiaDestinoBytes, nomeTecnologiaDestinoBytes);

    }

    public static void showDBLine (byte[] removidoBytes, byte[] grupoBytes, byte[] popularidadeBytes, byte[] pesoBytes, byte[] tamanhoTecnologiaOrigemBytes, byte[] nomeTecnologiaOrigemBytes, byte[] tamanhoTecnologiaDestinoBytes, byte[] nomeTecnologiaDestinoBytes) {
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
    public static void insertRegister() {
        System.out.println("Vamos inserir novos registros? \nDigite -1 para interromper ");
        String nomeTecnologiaOrigem , grupo , popularidade, nomeTecnologiaDestino, peso, removido;
        int tamanhoTecnologiaOrigem, tamanhoTecnologiaDestino;
        
        while (true) {
            System.out.println("Digite o nome da Tecnologia de Origem: ");
            nomeTecnologiaOrigem = input.nextLine();
            tamanhoTecnologiaOrigem = nomeTecnologiaOrigem.length();
            System.out.println("Digite o nome do grupo: ");
            grupo = input.nextLine();
            System.out.println("Digite a sua popularidade: ");
            popularidade = input.nextLine();
            System.out.println("Digite o nome da Tecnologia de destino: ");
            nomeTecnologiaDestino = input.nextLine();
            tamanhoTecnologiaDestino = nomeTecnologiaDestino.length();

        }
    }

    public static void readDB(){
        try (RandomAccessFile raf = new RandomAccessFile("tecnologia.csv", "rw")) {
            String line;

            // Ler e ignorar a primeira linha (cabeçalho)
            raf.readLine(); 

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

    
}
