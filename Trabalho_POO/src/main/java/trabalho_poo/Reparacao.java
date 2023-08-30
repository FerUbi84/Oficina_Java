/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_poo;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fermg
 */
public class Reparacao {

    private int idReparacao;
    private int idAvaria;
    private int nifCliente;
    private String nomeColaborador;
    private int pecasUtilizadas;
    static double maoObraHora = 6.30;
    private double valorTotal;

    public Reparacao() {
    }

    public Reparacao(int idReparacao) {
        this.idReparacao = idReparacao;
    }

    public Reparacao(int idReparacao, int idAvaria, int nifCliente, String nomeColaborador, int pecasUtilizadas, double valorTotal) {
        this.idReparacao = idReparacao;
        this.idAvaria = idAvaria;
        this.nifCliente = nifCliente;
        this.nomeColaborador = nomeColaborador;
        this.pecasUtilizadas = pecasUtilizadas;
        this.valorTotal = valorTotal;
    }

    public int getIdReparacao() {
        return idReparacao;
    }

    public void setIdReparacao(int idReparacao) {
        this.idReparacao = idReparacao;
    }

    public int getIdAvaria() {
        return idAvaria;
    }

    public void setIdAvaria(int idAvaria) {
        this.idAvaria = idAvaria;
    }

    public int getNifCliente() {
        return nifCliente;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public int getPecasUtilizadas() {
        return pecasUtilizadas;
    }

    public void setPecasUtilizadas(int pecasUtilizadas) {
        this.pecasUtilizadas = pecasUtilizadas;
    }

    public static double getMaoObraHora() {
        return maoObraHora;
    }

    public static void setMaoObraHora(double maoObraHora) {
        Reparacao.maoObraHora = maoObraHora;
    }

    public int pesquisaClienteAvaria(int nif, ArrayList<Avaria> avaria) {
        int nif_cliente = 0;
        for (Avaria a : avaria) {
            if (a.getNifCliente() == nif) {
                nif_cliente = a.getNifCliente();
                return nif_cliente;
            }
        }

        return -1;
    }

    public ArrayList<Reparacao> readFromFile(String file) throws FileNotFoundException {

        File insertFile = new File(file + ".txt");
        Scanner scanner = new Scanner(insertFile);

        ArrayList<Reparacao> reparacao = new ArrayList<Reparacao>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] items = line.split("\\|");

            int id_reparacao = Integer.parseInt(items[0]);
            int id_avaria = Integer.parseInt(items[1]);
            int nif_cliente = Integer.parseInt(items[2]);
            String nome_colaborador = items[3];
            int qtd_pecas = Integer.parseInt(items[4]);
            double valor_total = Double.parseDouble(items[5]);

            Reparacao rep = new Reparacao(id_reparacao, id_avaria, nif_cliente, nome_colaborador, qtd_pecas, valor_total);
            reparacao.add(rep);

        }
        scanner.close();
        return reparacao;
    }

    public void reparacao(int nifCliente) throws IOException {
        Scanner s = new Scanner(System.in);
        ArrayList<Reparacao> rep = new ArrayList<Reparacao>();
        ArrayList<Avaria> avaria = new ArrayList<Avaria>();
        ArrayList<Colaborador> c = new ArrayList<Colaborador>();
        Avaria a = new Avaria();
        Colaborador colaborador = new Colaborador();
        FileAction fileAction = new FileAction();
        Stock stock = new Stock();
        Stock st = new Stock();
        int nif_c = 0;
        boolean haveData = fileAction.checkFile("avarias");
        int inserir_reparacao = 1;
        String ficha_a = "";
        String failure_message = "Cliente não existente nas fichas de avarias";
        avaria = a.readFromFileAvaria("avarias");
        int autorizacaoCliente;
        int qtdPecas, pecasCompradas, horaDeObra;
        double valorPecasUtilizadas;
        String nome_c = "";
        String repe = "";

        if (haveData) {
            rep = readFromFile("reparacao");
        } else {
            System.out.println("Lista vazia");
        }
        if (inserir_reparacao != 0) {

            if (rep.isEmpty()) {
                idReparacao = 1;
            } else {
                idReparacao = rep.size() + 1;
            }

            System.out.println("Pesquise pelo nif do cliente na ficha de avarias para proceder à reparacao do equipamento do cliente:");
            nifCliente = s.nextInt();
            ficha_a = a.fichaAvaria(nifCliente, avaria);

            if (ficha_a.equals(failure_message)) {
                System.out.println("Cliente não existe na ficha de avaria,\nverifique se existe 1º se existe na ficha de \nclientes para proceder à inserção da ficha de avaria");
                inserir_reparacao = 0;
            } else {
                System.out.println(ficha_a + "\n");
                System.out.println("O cliente deseja fazer a reparação do equipamento?");
                System.out.println("1 para Sim\n0 para Não");
                autorizacaoCliente = s.nextInt();
                if (autorizacaoCliente == 0) {
                    System.out.println("O cliente decidiu não avançar com a reparação.");
                    inserir_reparacao = 0;
                } else {
                    System.out.println("O cliente decidiu avançar com a reparação.");
                    System.out.println("Insira o nome do colaborador para proceder à reparação:");
                    c = colaborador.readFromFileColaborador("colaborador");
                    nome_c = s.next();
                    String res_c = colaborador.pesquisaColaborador(nome_c, c);
                    while (res_c.equals("-1")) {
                        System.out.println(colaborador.toString());
                        System.out.println("Nome do colaborador inexistente ou inválido, insira de novo:");
                        nome_c = s.next();
                        res_c = colaborador.pesquisaColaborador(nome_c, c);
                    }
                    st = stock.readFromFile("stock");
                    System.out.println("Quantas peças necessárias para a repação?");
                    qtdPecas = s.nextInt();

                    while (qtdPecas > st.getQtdPecas()) {
                        System.out.println("Quantidade de peças pedidas superior ao stock,compre peças para repor o stock.");
                        System.out.println("Quantidas peças deseja comprar?");
                        pecasCompradas = s.nextInt();
                        st.comprarStock(pecasCompradas);
                        System.out.println("Quantas peças necessárias para a repação?");
                        qtdPecas = s.nextInt();
                    }
                    st.utilizarpecas(qtdPecas);
                    valorPecasUtilizadas = st.getPreco() * qtdPecas;
                    System.out.println("Quantas horas foram necessárias para a reparação necessárias para a repação?");
                    horaDeObra = s.nextInt();
                    valorTotal = valorPecasUtilizadas + (maoObraHora * horaDeObra);
                    int idAvaria = a.pesquisaAvaria(nifCliente, avaria);

                    // String id_avaria = String.valueOf(idAvaria);
                    //String nif_cliente = String.valueOf(nifCliente);
                    repe = idReparacao + "|" + idAvaria + "|" + nifCliente + "|" + res_c + "|" + qtdPecas + "|" + valorTotal;
                    fileAction.writeFile("reparacao", repe);
                }
            }
        }
    }

    @Override
    public String toString() {
        String ficha_avaria = "";
        try {
            ArrayList<Avaria> avarias = new ArrayList<>();
            Avaria avaria = new Avaria();
            avarias = avaria.readFromFileAvaria("avarias");
            ficha_avaria = avaria.fichaAvaria(nifCliente, avarias);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reparacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "FIcha de Reparacao\n" + ficha_avaria + "\nReparação:\nID Reparacao:" + idReparacao + "\nColaborador responsável reparação:" + nomeColaborador + "\nPeças Utilizadas=" + pecasUtilizadas + "\nValor Reparação=" + valorTotal + "€\n";
    }

}
