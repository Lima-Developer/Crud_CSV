package Domain.Models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
        System.arraycopy(dataBaseLine.getTamanhoTecnologiaOrigemBytes(), 0, dbLine, 13,
                dataBaseLine.getTamanhoTecnologiaOrigemBytes().length);
        System.arraycopy(dataBaseLine.getNomeTecnologiaOrigemBytes(), 0, dbLine, 17,
                dataBaseLine.getNomeTecnologiaOrigemBytes().length);
        System.arraycopy(dataBaseLine.getTamanhoTecnologiaDestinoBytes(), 0, dbLine, 37,
                dataBaseLine.getTamanhoTecnologiaDestinoBytes().length);
        System.arraycopy(dataBaseLine.getNomeTecnologiaDestinoBytes(), 0, dbLine, 41,
                dataBaseLine.getNomeTecnologiaDestinoBytes().length);

        // Grava os dados no arquivo dataBase.txt
        writeDataToFile();
    }

    private void writeDataToFile() {
        try (RandomAccessFile file = new RandomAccessFile("dataBase.txt", "rw")) {
            // Escreve a linha no arquivo
            file.seek(file.length()); // Move o ponteiro para o final do arquivo
            file.write(dbLine); // Grava a linha atual no arquivo
            file.writeBytes(System.lineSeparator()); // Adiciona uma nova linha após cada registro
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

        // Converte os tamanhos para inteiros
        int tamanhoOrigem = ByteBuffer.wrap(tamanhoOrigemBytes).getInt();
        int tamanhoDestino = ByteBuffer.wrap(tamanhoDestinoBytes).getInt();

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
                removido, grupo, popularidade, peso,
                tamanhoOrigem, nomeOrigem,
                tamanhoDestino, nomeDestino);

        // Exibe o resultado formatado
        System.out.println(resultado);
    }

    public void deleteRegister(int line) {
        int count = 0;
        for (byte Line : dbLine) {
            count++;
            if (line == count && line != 0) {
                System.out.println(Line);
                break;
            }
        }
    }

    public void undeleteRegister(int line) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'undeleteRegister'");
    }
}
