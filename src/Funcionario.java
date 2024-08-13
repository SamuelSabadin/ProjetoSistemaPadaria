import java.util.ArrayList;
import java.util.Scanner;

public class Funcionario extends Pessoa implements IPessoa
{
    private static ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
    private int idFunc;
    private String cpfFunc,telefoneFunc, senhaFunc;
    private static String pesquisaFunc;
    private static Scanner scannerPesquisa = new Scanner(System.in);

    public Funcionario(String nome, int idade, String cpfFunc, String telefoneFunc, int idFunc, String senhaFunc)
    {
        super(nome,idade);
        try
        {
            if(idade<0)
                throw new IllegalArgumentException();
            if(Double.parseDouble(cpfFunc)<0)
                throw new IllegalArgumentException();
            else if(cpfFunc.length()!=11)
                throw new LengthException();
            else
                this.cpfFunc = cpfFunc;
            if(Double.parseDouble(telefoneFunc)<0)
                throw new IllegalArgumentException();
            else
                this.telefoneFunc = telefoneFunc;
            if(idFunc<=0)
                throw new IllegalArgumentException();
            else
                this.idFunc = idFunc;
            if(senhaFunc.length()<8)
                throw new LengthException();
            else
                this.senhaFunc = senhaFunc;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Um ou mais valores foram digitados incorretamente!");
        }
        catch(LengthException e)
        {
            System.out.println("Senha deve ter 8 ou mais caracteres/O CPF do funcionário foi digitado incorretamente!");
        }
        catch(NullPointerException e)
        {
            System.out.println("Não são permitidos valores nulos!");
        }
    }
    @Override
    public String exibir()//corrigir pois não está exibindo
    {
        return "Nome do funcionário: "+getNome()+"\nIdade do funcionário: "+getIdade()+"\nCPF do funcionário: "+cpfFunc+"\nTelefone do funcionário: "+telefoneFunc+"\nID do funcionário: "+idFunc+"\n";
    }
    public static void cadastrar(Funcionario funcionario)
    {
        listaFuncionario.add(funcionario);
    }
    public static void excluir(int id)
    {
        ArrayList<Funcionario> listaTemp = new ArrayList<>();
        for(Funcionario f : listaFuncionario)
        {
            if(f.idFunc == id)
            {
                listaTemp.add(f);
            }
        }
        if(listaTemp.isEmpty())
            System.out.println("Não foi encontrado nenhum funcionário com o ID solicitado!\n");
        else
            listaFuncionario.removeAll(listaTemp);
    }
    public static void exibeFuncionarios()
    {
        for(Funcionario f : listaFuncionario)
        {
            System.out.println(f.exibir());
        }
    }
    public static void pesquisa()
    {
        int qtd = 0;
        pesquisaFunc = scannerPesquisa.nextLine();
        for(Funcionario f : listaFuncionario)
        {
            if(f.getNome().startsWith(pesquisaFunc))
            {
                System.out.println(f.exibir());
                qtd=1;
            }
        }
        if(qtd == 0)
        {
            System.out.println("Nenhum funcionário foi encontrado...");
        }
    }
    public static ArrayList<Funcionario> getListaFuncionario(){
        return listaFuncionario;
    }
    public String getCpfFunc(){
        return cpfFunc;
    }
    public String getTelefoneFunc(){
        return telefoneFunc;
    }
    public int getIdFunc(){
        return idFunc;
    }
    public String getSenhaFunc()
    {
        return senhaFunc;
    }
}