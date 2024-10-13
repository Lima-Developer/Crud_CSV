import Domain.Models.DataBase;
import Domain.Models.DataBaseLine;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        lerCSV("tecnologia.csv", "r");
    }

    public static void lerCSV(String inputFile, String mode) {
        try(RandomAccessFile raf = new RandomAccessFile(inputFile, mode)) {
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
            System.out.println("Total de linhas processadas: " + numLinhas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
