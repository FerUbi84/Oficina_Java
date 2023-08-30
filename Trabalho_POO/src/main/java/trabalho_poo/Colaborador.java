package trabalho_poo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class Colaborador extends Cliente {

    private int idColaborador;
    private String nomeColaborador;

    public Colaborador() {
    }
    

    public Colaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }


    Colaborador(int idColaborador, String nomeColaborador) {
        this.idColaborador = idColaborador;
        this.nomeColaborador = nomeColaborador;
    }

    
    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String pesquisaColaborador(String nome, ArrayList<Colaborador> colaborador) {

        for (Colaborador col : colaborador) {
            if (col.getNomeColaborador().equals(nome)) {
                return nome;
            }
        }
        return nome = "-1";
    }

    public ArrayList<Colaborador> readFromFileColaborador(String file) throws FileNotFoundException {

        File insertFile = new File(file + ".txt");
        ArrayList<Colaborador> colaboradores;
        try (Scanner scanner = new Scanner(insertFile)) {
            colaboradores = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split("\\|");
                
                int id = Integer.parseInt(items[0]);
                String nome = items[1];
                
                Colaborador colaborador = new Colaborador(id, nome);
                colaboradores.add(colaborador);
                
            }
        }
        return colaboradores;
    }

    @Override
    public String toString() {
        return "\nID Colaborador=" + idColaborador + "\nNome Colaborador=" + nomeColaborador;
    }
    

    public ArrayList<Colaborador> insertColaborador(int id, String nome) throws IOException {
        Scanner s = new Scanner(System.in);
        ArrayList<Colaborador> colaborador = new ArrayList<>();
        FileAction fileAction = new FileAction();
        int inserir_colaborador = 1;
        boolean haveData = fileAction.checkFile("colaborador");

        if (haveData) {
            colaborador = readFromFileColaborador("colaborador");
        } else {
            System.out.println("Lista vazia");
        }

        while (inserir_colaborador != 0) {

            if (colaborador.isEmpty()) {
                id = 1;
            } else {
                id = colaborador.size() + 1;
            }

            System.out.println("Insira o nome do colaborador: ");
            nome = s.next();
            Colaborador c = new Colaborador(id, nome);
            colaborador.add(c);

            System.out.println("Deseja inserir mais algum colaborador?\n");
            System.out.println("1 - SIM\n");
            System.out.println("0 - NAO\n");
            inserir_colaborador = s.nextInt();

            String infoColaborador = id + "|" + nome;
            fileAction.writeFile("colaborador", infoColaborador);
        }

        return colaborador;
    }
}
