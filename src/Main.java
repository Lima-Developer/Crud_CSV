import Domain.Interfaces.CSV;
import Domain.Models.DataBase;

import java.io.IOException;
import java.util.Scanner;

public class Main implements CSV {
    protected static DataBase dataBase = new DataBase();
    protected static String filePath;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 7) {
            System.out.println("\n================ Menu =================");
            System.out.println("1- Cadastrar banco de dados");
            System.out.println("2- Exibir banco de dados");
            System.out.println("3- Inserir registro");
            System.out.println("4- Atualizar registo");
            System.out.println("5- Deletar registro");
            System.out.println("6- Recuperar registros removidos");
            System.out.println("7- Sair");
            System.out.println("=======================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Create Table
                    System.out.print("Informe o nome do arquivo .csv: ");

                    filePath = scanner.nextLine();
                    dataBase = CSV.readCSV(filePath);
                    break;
                case 2:
                    // Select Table
                    try {
                        dataBase.selectDataBase("dataBase.txt");
                    } catch (IOException e) {
                        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                    }
                    continue;
                case 3:
                    // Insert Register
                    continue;
                case 4:
                    // Update Register
                    int rrn;
                    System.out.print("Insira o número do registro que deseja atualizar: ");
                    rrn = scanner.nextInt();

                    try {
                        dataBase.updateRegister(rrn);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    continue;
                case 5:
                    // Delete Register
                    try {
                        System.out.println("Informe a linha do registro que quer deletar: ");

                        rrn = scanner.nextInt();
                        dataBase.deleteRegister(rrn);
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro, voltando para o menu.");
                        System.out.println("-------------------------------------");
                        main(args);
                    }
                    continue;
                case 6:
                    // Undelete Register
                    try {
                        System.out.print("Informe a linha do registro que quer recuperar: ");

                        rrn = scanner.nextInt();
                        dataBase.undeleteRegister(rrn);
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro, voltando para o menu.");
                        System.out.println("-------------------------------------");
                        main(args);
                    }
                    continue;
                case 7:
                    System.out.println("Saindo do programa");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
