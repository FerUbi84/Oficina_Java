package trabalho_poo;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner s = new Scanner(System.in);
        int nav_menu = -1;

        //Cliente
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = new Cliente();
        int id_cliente = 0, nif_cliente = 0;
        String nome_cliente = null;
        //ENDOF Cliente

        //Avaria
        ArrayList<Avaria> avarias = new ArrayList<Avaria>();
        Avaria avaria = new Avaria();
        int id_avaria = 0, id_tipo_produto = 0, id_tipo_avaria = 0;
        //ENDOF Avaria

        //Reparacao
        ArrayList<Reparacao> reparacoes = new ArrayList<Reparacao>();
        Reparacao reparacao = new Reparacao();
        //ENDOF Reparacao

        //Colaborador
        ArrayList<Colaborador> colaboradores = new ArrayList<Colaborador>();
        Colaborador colaborador = new Colaborador();
        int id_colaborador = 0;
        String nome_colaborador = "";
        //ENDOF Colaborador

        //Stock
        Stock stock = new Stock();
        Stock st = new Stock();
        //ENDOF Stock

        System.out.println("\t\tTRABALHO POO - Paul Crocker");
        System.out.println("\tAugusto Bernardino / Fernando Gomes");

        System.out.println("\nOficina de reparacções AUGO");
        System.out.println("1-Adicionar cliente\n2-Verificar clientes inseridos na ficha de clientes\n3-Criar ficha de Avaria\n4-Verificar fichas de Avaria\n5-Reparar Equipamento\n6-Verificar Reparações\n7-Stock\n8-Inserir Colaboradores\n9-Verificar Colaboradores");
        System.out.println("Selecione a Opção pretendida:");
        nav_menu = s.nextInt();

        while (nav_menu != 0) {
            if (nav_menu == 1) {
                System.out.println("\tAdicionar cliente");
                cliente.insertCliente(id_cliente, nome_cliente, nif_cliente);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 2) {
                System.out.println("\tVerificar clientes inseridos na ficha de clientes");
                clientes = cliente.readFromFile("clientes");
                System.out.println(clientes);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 3) {
                System.out.println("\tCriar ficha de Avaria");
                avaria.insertAvaria(id_avaria, id_tipo_produto, nif_cliente, id_tipo_avaria);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 4) {
                System.out.println("\tVerificar fichas de Avaria");
                avarias = avaria.readFromFileAvaria("avarias");
                System.out.println(avarias);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 5) {
                System.out.println("\tReparar Equipamento");
                reparacao.reparacao(nif_cliente);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 6) {
                System.out.println("\tVerificar Reparações");
                reparacoes = reparacao.readFromFile("reparacao");
                System.out.println(reparacoes);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 7) {
                System.out.println("\tStock");
                st = stock.readFromFile("stock");
                System.out.println(st);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 8) {
                System.out.println("\tInserir Colaboradores");
                colaborador.insertColaborador(id_colaborador, nome_colaborador);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            } else if (nav_menu == 9) {
                System.out.println("\tVerificar Colaboradores");
                colaboradores = colaborador.readFromFileColaborador("colaborador");
                System.out.println(colaboradores);
                System.out.println("Slecione outra opção, caso queira sair escreva 0");
                nav_menu = s.nextInt();
            }
        }
    }
}
