package Domain.Interfaces;

import Domain.Models.DataBase;
import Domain.Models.DataBaseLine;

import java.io.IOException;
import java.io.RandomAccessFile;

public interface CSV {

    static void readCSV(String inputFile) {
        try(RandomAccessFile raf = new RandomAccessFile(inputFile.concat(".csv"), "r")) {
            String line;

            raf.readLine(); // Ignorando linha do cabeçalho

            int numLinhas = 0;

            // Criando a instancia do banco de dados
            DataBase dataBase = new DataBase();

            while ((line = raf.readLine()) != null ) {
                String[] values = line.split(",");
                DataBaseLine dataBaseLine = new DataBaseLine(values);

                // Criando a tabela com base na linha atual
                dataBase.createTable(dataBaseLine);

                // Exibindo a linha armazenada no banco de dados
                dataBase.showDBLine();

                numLinhas++;
            }
            System.out.println("\nTotal de linhas processadas: " + numLinhas);

            System.out.println("\n|================= TABELA NO BANCO DE DADOS CRIADA COM SUCESSO! =================|");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
