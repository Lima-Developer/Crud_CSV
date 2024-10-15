package Domain.Models;

import java.util.Scanner;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DataBase {
    private byte[] dbLine;
    private boolean headerPrinted; // Variável para controlar a impressão do cabeçalho

    public DataBase() {
        this.dbLine = new byte[76];
        this.headerPrinted = false; // Inicializa como falso
    }

    public byte[] getDbLine() {
        return dbLine;
    }

    public void setDbLine(byte[] dbLine) {
        this.dbLine = dbLine;
    }

    public void createTable(DataBaseLine dataBaseLine) {
        // Limpa o array dbLine preenchendo com '*'
        Arrays.fill(dbLine, (byte) '*');

        // Insere os dados nas respectivas posições do array
        System.arraycopy(dataBaseLine.getRemovidoBytes(), 0, dbLine, 0, dataBaseLine.getRemovidoBytes().length);
        System.arraycopy(dataBaseLine.getGrupoBytes(), 0, dbLine, 1, dataBaseLine.getGrupoBytes().length);
        System.arraycopy(dataBaseLine.getPopularidadeBytes(), 0, dbLine, 5, dataBaseLine.getPopularidadeBytes().length);
        System.arraycopy(dataBaseLine.getPesoBytes(), 0, dbLine, 9, dataBaseLine.getPesoBytes().length);
        System.arraycopy(dataBaseLine.getTamanhoTecnologiaOrigemBytes(), 0, dbLine, 13, dataBaseLine.getTamanhoTecnologiaOrigemBytes().length);
        System.arraycopy(dataBaseLine.getNomeTecnologiaOrigemBytes(), 0, dbLine, 17, dataBaseLine.getNomeTecnologiaOrigemBytes().length);
        System.arraycopy(dataBaseLine.getTamanhoTecnologiaDestinoBytes(), 0, dbLine, 37, dataBaseLine.getTamanhoTecnologiaDestinoBytes().length);
        System.arraycopy(dataBaseLine.getNomeTecnologiaDestinoBytes(), 0, dbLine, 41, dataBaseLine.getNomeTecnologiaDestinoBytes().length);

        // Grava os dados no arquivo dataBase.txt
        writeDataToFile();
    }

    private void writeDataToFile() {
        try (RandomAccessFile file = new RandomAccessFile("dataBase.txt", "rw")) {
            file.seek(file.length());
            file.write(dbLine);
        } catch (IOException e) {
            e.printStackTrace(); // Lida com exceções de IO
        }
    }

    public void showDBLine() {
        // Extrai o campo 'removido' (primeiro byte)
        byte removidoBytes = dbLine[0];
        String removido = String.valueOf((char) removidoBytes);

        // Extrai os tamanhos de origem e destino
        byte[] tamanhoOrigemBytes = Arrays.copyOfRange(dbLine, 13, 17);
        byte[] tamanhoDestinoBytes = Arrays.copyOfRange(dbLine, 37, 41);

        // Extrai os demais campos
        byte[] grupoBytes = Arrays.copyOfRange(dbLine, 1, 5);
        byte[] popularidadeBytes = Arrays.copyOfRange(dbLine, 5, 9);
        byte[] pesoBytes = Arrays.copyOfRange(dbLine, 9, 13);
        byte[] nomeOrigemBytes = Arrays.copyOfRange(dbLine, 17, 37); // Assume o campo de origem é de tamanho fixo
        byte[] nomeDestinoBytes = Arrays.copyOfRange(dbLine, 41, 61); // Assume o campo de destino é de tamanho fixo

        // Converte os campos para strings e substitui '*' por espaços para uma
        // visualização mais limpa
        String grupo = new String(grupoBytes, StandardCharsets.UTF_8).replace("*", " ");
        String popularidade = new String(popularidadeBytes, StandardCharsets.UTF_8).replace("*", " ");
        String peso = new String(pesoBytes, StandardCharsets.UTF_8).replace("*", " ");
        String nomeOrigem = new String(nomeOrigemBytes, StandardCharsets.UTF_8).replace("*", " ");
        String nomeDestino = new String(nomeDestinoBytes, StandardCharsets.UTF_8).replace("*", " ");
        String tamanhoOrigem = new String(tamanhoOrigemBytes, StandardCharsets.UTF_8).replace("*", " ");
        String tamanhoDestino = new String(tamanhoDestinoBytes, StandardCharsets.UTF_8).replace("*", " ");

        // Imprime o cabeçalho apenas se ainda não foi impresso
        if (!headerPrinted) {
            System.out.println(String.format("\n%-12s | %-6s | %-12s | %-4s | %-16s | %-12s | %-16s | %-12s", "STATUS", "GRUPO", "POPULARIDADE", "PESO", "TAMANHO ORIGEM", "NOME ORIGEM", "TAMANHO DESTINO", "NOME DESTINO"));
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            headerPrinted = true; // Marca como impresso
        }

        // Formata a saída de forma mais amigável
        String resultado = String.format("%-12s | %-6s | %-12s | %-4s | %-16s | %-12s | %-16s | %-12s", removido, grupo, popularidade, peso, tamanhoOrigem, nomeOrigem, tamanhoDestino, nomeDestino);

        // Exibe o resultado formatado
        System.out.println(resultado);
    }

    public void deleteRegister(int register) {
        try (RandomAccessFile raf = new RandomAccessFile("dataBase.txt", "rw")) {
            String line;
            long pointer = 0;
            if (register == 0) throw new RuntimeException("Não é possível alterar o cabeçalho");
            int count = 1;

            while ((line = raf.readLine()) != null) {
                byte[] bLine = line.getBytes(StandardCharsets.UTF_8);

                if (register == count) {
                    bLine[0] = '1';
                    raf.seek(pointer);
                    raf.write(bLine);

                    System.out.println("\nRegistro " + register + " marcado como removido!");
                    break;
                } else {
                    pointer = raf.getFilePointer();
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void undeleteRegister(int register) {
        try (RandomAccessFile raf = new RandomAccessFile("dataBase.txt", "rw")) {
            String line;
            long pointer = 0;
            int count = 1;

            while ((line = raf.readLine()) != null) {
                byte[] bLine = line.getBytes(StandardCharsets.UTF_8);

                if (register == count) {
                    bLine[0] = '0';
                    raf.seek(pointer);
                    raf.write(bLine);
                    System.out.println("\nRegistro " + register + " marcado como removido!");
                    break;
                } else {
                    pointer = raf.getFilePointer(); // Atualiza a posição do ponteiro antes da próxima leitura
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectDataBase(String filePath) throws IOException {
        boolean headerPrinted = false; // Variável para controlar a impressão do cabeçalho
        Set<String> tecnologias = new HashSet<>(); // Conjunto para armazenar as tecnologias únicas
        Set<String> paresTecnologias = new HashSet<>(); // Conjunto para armazenar os pares de tecnologias únicas

        final int RECORD_SIZE = 76; // Tamanho fixo de cada registro

        // Abre o arquivo para leitura
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            byte[] dbLine = new byte[RECORD_SIZE]; // Buffer para ler cada linha (76 bytes)

            // Lê o arquivo em blocos de 76 bytes
            while (file.read(dbLine) != -1) {
                
                // Extrai o campo 'removido' (primeiro byte)
                byte removidoBytes = dbLine[0];
                String removido = String.valueOf((char) removidoBytes);

                // Pula registros marcados como logicamente removidos ('1')
                if (removido.equals("1")) {
                    continue;
                }

                // Extrai os tamanhos de origem e destino
                byte[] tamanhoOrigemBytes = Arrays.copyOfRange(dbLine, 13, 17);
                byte[] tamanhoDestinoBytes = Arrays.copyOfRange(dbLine, 37, 41);

                // Converte os tamanhos para inteiros
                int tamanhoOrigem = ByteBuffer.wrap(tamanhoOrigemBytes).getInt();
                int tamanhoDestino = ByteBuffer.wrap(tamanhoDestinoBytes).getInt();

                // Extrai os demais campos
                byte[] grupoBytes = Arrays.copyOfRange(dbLine, 1, 5);
                byte[] popularidadeBytes = Arrays.copyOfRange(dbLine, 5, 9);
                byte[] pesoBytes = Arrays.copyOfRange(dbLine, 9, 13);
                byte[] nomeOrigemBytes = Arrays.copyOfRange(dbLine, 17, 37); // 20 bytes
                byte[] nomeDestinoBytes = Arrays.copyOfRange(dbLine, 41, 61); // 20 bytes

                // Converte os campos para strings e substitui '*' por espaços para uma visualização mais limpa
                String grupo = new String(grupoBytes, StandardCharsets.UTF_8).replace("*", " ");
                String popularidade = new String(popularidadeBytes, StandardCharsets.UTF_8).replace("*", " ");
                String peso = new String(pesoBytes, StandardCharsets.UTF_8).replace("*", " ");
                String nomeOrigem = new String(nomeOrigemBytes, StandardCharsets.UTF_8).replace("*", " ");
                String nomeDestino = new String(nomeDestinoBytes, StandardCharsets.UTF_8).replace("*", " ");

                // Adiciona as tecnologias de origem e destino ao Set
                tecnologias.add(nomeOrigem.trim());
                tecnologias.add(nomeDestino.trim());

                // Cria um par único, independentemente da ordem (ordem alfabética)
                String parTecnologias = (nomeOrigem.compareTo(nomeDestino) < 0) 
                        ? nomeOrigem.trim() + " - " + nomeDestino.trim() 
                        : nomeDestino.trim() + " - " + nomeOrigem.trim();

                // Adiciona o par ao Set de pares únicos
                paresTecnologias.add(parTecnologias);

                // Imprime o cabeçalho apenas se ainda não foi impresso
                if (!headerPrinted) {
                    System.out.println(String.format("\n%-12s | %-6s | %-12s | %-4s | %-16s | %-12s | %-16s | %-12s",
                            "STATUS", "GRUPO", "POPULARIDADE", "PESO", "TAMANHO ORIGEM", "NOME ORIGEM",
                            "TAMANHO DESTINO", "NOME DESTINO"));
                    System.out.println(
                            "----------------------------------------------------------------------------------------------------------------");
                    headerPrinted = true; // Marca como impresso
                }

                // Formata a saída de forma mais amigável
                String resultado = String.format("%-12s | %-6s | %-12s | %-4s | %-16d | %-12s | %-16d | %-12s",
                        removido, grupo, popularidade, peso, tamanhoOrigem, nomeOrigem, tamanhoDestino, nomeDestino);

                // Exibe o resultado formatado
                System.out.println(resultado);
            }
        }

        // Chama o método para contar e exibir as tecnologias diferentes e os pares de tecnologias
        contarTecnologias(tecnologias);
        contarParesTecnologias(paresTecnologias);
    }

    // Método para contar e exibir o número de tecnologias diferentes
    public static void contarTecnologias(Set<String> tecnologias) {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        System.out.println("\nNúmero total de tecnologias diferentes: " + tecnologias.size());

        System.out.println("\nDeseja exibir a lista de tecnologias únicas? (S/N)");
        String resposta = input.nextLine().toUpperCase();

        if (resposta.equals("S")) {
            System.out.println("Lista de Tecnologias Únicas:");
            for (String tecnologia : tecnologias) {
                System.out.println("- " + tecnologia);
            }
        } else {
            System.out.println("Operação concluída.");
        }     
    }

    // Método para contar e exibir os pares de tecnologias únicos
    public static void contarParesTecnologias(Set<String> paresTecnologias) {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        System.out.println("\nNúmero total de pares de tecnologias únicos: " + paresTecnologias.size());

        System.out.println("\nDeseja exibir a lista de pares de tecnologias únicas? (S/N)");
        String resposta = input.nextLine().toUpperCase();

        if (resposta.equals("S")) {
            System.out.println("Lista de Pares de Tecnologias Únicos:");
            for (String par : paresTecnologias) {
                System.out.println("- " + par);
            }
        } else {
            System.out.println("Operação concluída.");
        }
    }
}