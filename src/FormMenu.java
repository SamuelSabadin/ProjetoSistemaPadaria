import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.formdev.flatlaf.FlatDarkLaf;
public class FormMenu extends JFrame
{
    public FormMenu()
    {
        setTitle("Padoca: área de funcionário");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        GridBagConstraints coords = new GridBagConstraints();
        Insets espacamento = new Insets(0,0,40,0);
        Insets semEspacamento = new Insets(0,0,0,0);

        JButton buttonProd = new JButton("Cadastrar/Pesquisar Produtos");
        buttonProd.setPreferredSize(new Dimension(450,50));
        JButton buttonFunc = new JButton("Cadastrar/Pesquisar Funcionários");
        buttonFunc.setPreferredSize(new Dimension(450,50));
        JButton buttonCliente = new JButton("Cadastrar/Pesquisar Clientes");
        buttonCliente.setPreferredSize(new Dimension(450,50));

        buttonProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroProduto f = new FormCadastroProduto();
                dispose();
            }
        });
        buttonFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroFuncionario f = new FormCadastroFuncionario();
                dispose();
            }
        });
        buttonCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormCadastroCliente f = new FormCadastroCliente();
                dispose();
            }
        });
        JButton voltar = new JButton("Voltar à vitrine");
        voltar.setPreferredSize(new Dimension(450,50));

        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Telas.atulizaV();
                Telas.setI(1);
                Telas.troca();
            }
        });

        JPanel panelCenter = new JPanel(new GridBagLayout());
        coords.gridx = 0;
        coords.gridy = 0;
        coords.insets = espacamento;
        panelCenter.add(new JLabel("Bem-vindo à área de funcionários da Padoca! O que deseja fazer?"),coords);
        coords.gridy = 1;
        panelCenter.add(buttonProd,coords);
        coords.gridy = 2;
        panelCenter.add(buttonFunc,coords);
        coords.gridy = 3;
        panelCenter.add(buttonCliente,coords);
        coords.gridy = 4;
        panelCenter.add(voltar,coords);

        add(panelCenter,BorderLayout.CENTER);
        setVisible(true);
    }


}
