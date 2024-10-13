import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("================ Menu =================");
            System.out.println("1- Exibir banco de dados");
            System.out.println("2- Inserir registro");
            System.out.println("3- Atualizar registo");
            System.out.println("4- Deletar registro");
            System.out.println("5- Recuperar registros removidos");
            System.out.println("6- Sair");
            System.out.println("=======================================");
            System.out.print("Escolha uma opção: ");

            String opcao = input.nextLine();

            // Para fim de testes, o programa faz apenas o manuseio do arquivo CSV disponibilizado pelo professor 
            switch (opcao) {
                case "1":
                    Dbfunctions.readDB();
                    continue;
                case "2":
                    Dbfunctions.insertRegister();
                    continue;
                case "3":
                    // Dbfunctions.updateReg();
                    continue;
                case "4":
                    // Dbfunctions.deleteReg();
                    continue;
                case "5":
                    // Dbfunctions.recoverReg();
                case "6":
                System.out.println("Saindo do programa");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

    }

    

}
