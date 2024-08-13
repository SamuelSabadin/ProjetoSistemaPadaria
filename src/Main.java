import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main
{
    public static void main(String[] args)
    {
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }
        catch(UnsupportedLookAndFeelException e)
        {
            System.out.println("Deu errado");
        }
        System.out.println("Bem-vindo à padoca");
        Produto p1 = new Produto("Massa crocante e recheada","Torta",8.00,10,"1234567890123");
        p1.setImagem(new ImageIcon("imgs/torta.jpeg"));
        Produto p2 = new Produto("Pão macio com casca crocante.","Pao francês",6.00,10,"2345678901234");
        p2.setImagem(new ImageIcon("imgs/paofrances.jpeg"));
        Produto p3 = new Produto("Café forte e sem açucar","Café",4.50,10,"3456789012345");
        p3.setImagem(new ImageIcon("imgs/café.jpeg"));
        Produto p4 = new Produto("Capuccino café,leite e achocolatado","Capuccino",4.50,10,"3456789012345");
        p4.setImagem(new ImageIcon("imgs/capuccino.jpeg"));
        Produto p5 = new Produto("Massa foleada recheada com frango desfiado","Salgado",4.50,10,"3456789012345");
        p5.setImagem(new ImageIcon("imgs/salgado.jpeg"));
        Produto.cadastrar(p1);
        Produto.cadastrar(p2);
        Produto.cadastrar(p3);
        Produto.cadastrar(p4);
        Produto.cadastrar(p5);
        Funcionario.cadastrar(new Funcionario("Admin",0,"00000000000","0",74,"123456789"));
       Telas.Inicializa();
    }
}