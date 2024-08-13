import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FormCadastroProduto extends JFrame
{
    public FormCadastroProduto()
    {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                FormMenu f = new FormMenu();
            }
        });

        setTitle("Cadastrar produto");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        GridBagConstraints coords = new GridBagConstraints();
        Insets espacamento = new Insets(0,0,15,0);
        Insets semEspacamento = new Insets(0,0,0,0);

        JMenuBar barPesquisa = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        barPesquisa.add(menu);
        JMenuItem menuPesquisa = new JMenuItem("Pesquisar produto");
        menu.add(menuPesquisa);

        JTextField fieldNome = new JTextField();
        fieldNome.setPreferredSize(new Dimension(250,25));
        JTextField fieldDesc = new JTextField();
        fieldDesc.setPreferredSize(new Dimension(250,25));
        JTextField fieldPreco = new JTextField();
        fieldPreco.setPreferredSize(new Dimension(250,25));
        JTextField fieldCodBarras = new JTextField();
        fieldCodBarras.setPreferredSize(new Dimension(250,25));
        JTextField fieldValidade = new JTextField();
        fieldValidade.setPreferredSize(new Dimension(250,25));
        JTextField fieldRemover = new JTextField();
        fieldRemover.setPreferredSize(new Dimension(250,25));
        JTextField fieldFoto = new JTextField();
        fieldFoto.setPreferredSize(new Dimension(250,25));

        JButton buttonCadastrar = new JButton("Cadastrar Produto");
        buttonCadastrar.setPreferredSize(new Dimension(250,25));
        JButton buttonRemover = new JButton("Excluir Produto");
        buttonRemover.setPreferredSize(new Dimension(250,25));

        JPanel painelCentral = new JPanel(new GridBagLayout());
        coords.gridx = 0;
        coords.gridy = 0;
        painelCentral.add(new JLabel("Nome do produto"),coords);
        coords.gridy = 1;
        coords.insets = espacamento;
        painelCentral.add(fieldNome,coords);
        coords.gridy = 2;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Descrição do Produto"),coords);
        coords.gridy = 3;
        coords.insets = espacamento;
        painelCentral.add(fieldDesc,coords);
        coords.gridy = 4;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Preço do Produto"),coords);
        coords.gridy = 5;
        coords.insets = espacamento;
        painelCentral.add(fieldPreco,coords);
        coords.gridy = 6;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Código de Barras"),coords);
        coords.gridy = 7;
        coords.insets = espacamento;
        painelCentral.add(fieldCodBarras,coords);
        coords.gridy = 8;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Validade do Produto"),coords);
        coords.gridy = 9;
        coords.insets = espacamento;
        painelCentral.add(fieldValidade,coords);
        coords.gridy = 10;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Nome e diretório da foto(ex: imgs/x.jpeg):"),coords);
        coords.gridy = 11;
        coords.insets = espacamento;
        painelCentral.add(fieldFoto,coords);
        coords.gridy = 12;
        painelCentral.add(buttonCadastrar,coords);
        coords.gridy = 13;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Excluir produto(Código de Barras)"),coords);
        coords.gridy = 14;
        coords.insets = espacamento;
        painelCentral.add(fieldRemover,coords);
        coords.gridy = 15;
        painelCentral.add(buttonRemover,coords);

        add(painelCentral,BorderLayout.CENTER);
        add(barPesquisa,BorderLayout.NORTH);

        setVisible(true);

        menuPesquisa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                FormPesquisaProduto f = new FormPesquisaProduto();
            }
        });


        buttonCadastrar.addActionListener(new ActionListener() {
            boolean infoJaExiste = false;
            public void actionPerformed(ActionEvent e) {
                try{
                    for(Produto p : Produto.getListaProduto())
                    {
                        if(fieldCodBarras.getText().equals(p.getCodigo_barras()))
                            infoJaExiste = true;
                    }
                    if(fieldCodBarras.getText().equals("")||fieldNome.getText().equals("")||fieldDesc.getText().equals("")||fieldPreco.getText().equals("")||fieldValidade.getText().equals("")){
                        throw new IllegalArgumentException();
                    }
                    else if(Double.parseDouble(fieldCodBarras.getText())<=0||Double.parseDouble(fieldPreco.getText())<=0||Integer.parseInt(fieldValidade.getText())<=0){
                        throw new IllegalArgumentException();
                    }
                    else if(fieldCodBarras.getText().length()!=13){
                        throw new LengthException();
                    }
                    else if(infoJaExiste==true){
                        throw new IllegalArgumentException();
                    }
                    else{
                        Produto p = new Produto(fieldDesc.getText(),fieldNome.getText(),Double.parseDouble(fieldPreco.getText()),Integer.parseInt(fieldValidade.getText()),fieldCodBarras.getText());
                        p.novasetimagem(fieldFoto.getText());
                        Produto.cadastrar(p);
                        fieldNome.setText(null);
                        fieldDesc.setText(null);
                        fieldPreco.setText(null);
                        fieldCodBarras.setText(null);
                        fieldValidade.setText(null);
                        fieldFoto.setText(null);
                        JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                    }
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null,"Insira valores válidos!");
                }
                catch(LengthException ex){
                    JOptionPane.showMessageDialog(null,"Digite o código de barras corretamente!");
                }
            }
        });
        buttonRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean produtoExiste = false;
                for(Produto p : Produto.getListaProduto()){
                    if(fieldRemover.getText().equals(p.getCodigo_barras()))
                        produtoExiste = true;
                }
                if(produtoExiste == false)
                {
                    JOptionPane.showMessageDialog(null,"Produto não encontrado!");
                    fieldRemover.setText(null);
                }
                else
                {
                    Produto.excluir(fieldRemover.getText());
                    fieldRemover.setText(null);
                    JOptionPane.showMessageDialog(null,"Produto excluído com sucesso!");
                }

            }
        });
    }
}