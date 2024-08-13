import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FormCadastroCliente extends JFrame
{
    public FormCadastroCliente()
    {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                FormMenu f = new FormMenu();
            }
        });

        setTitle("Cadastrar cliente");
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
        JMenuItem menuPesquisa = new JMenuItem("Pesquisar cliente");
        menu.add(menuPesquisa);

        JTextField fieldNome = new JTextField();
        fieldNome.setPreferredSize(new Dimension(250,25));
        JTextField fieldIdade = new JTextField();
        fieldIdade.setPreferredSize(new Dimension(250,25));
        JTextField fieldCpf = new JTextField();
        fieldCpf.setPreferredSize(new Dimension(250,25));
        JTextField fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension(250,25));
        JTextField fieldTelefone = new JTextField();
        fieldTelefone.setPreferredSize(new Dimension(250,25));
        JTextField fieldRemover = new JTextField();
        fieldRemover.setPreferredSize(new Dimension(250,25));

        JButton buttonCadastrar = new JButton("Cadastrar Cliente");
        buttonCadastrar.setPreferredSize(new Dimension(250,25));
        JButton buttonRemover = new JButton("Excluir Cliente");
        buttonRemover.setPreferredSize(new Dimension(250,25));

        JPanel painelCentral = new JPanel(new GridBagLayout());
        coords.gridx = 0;
        coords.gridy = 0;
        painelCentral.add(new JLabel("Nome do Cliente"),coords);
        coords.gridy = 1;
        coords.insets = espacamento;
        painelCentral.add(fieldNome,coords);
        coords.gridy = 2;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Idade do Cliente"),coords);
        coords.gridy = 3;
        coords.insets = espacamento;
        painelCentral.add(fieldIdade,coords);
        coords.gridy = 4;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("CPF do Cliente"),coords);
        coords.gridy = 5;
        coords.insets = espacamento;
        painelCentral.add(fieldCpf,coords);
        coords.gridy = 6;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("E-mail do Cliente"),coords);
        coords.gridy = 7;
        coords.insets = espacamento;
        painelCentral.add(fieldEmail,coords);
        coords.gridy = 8;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Telefone do Cliente"),coords);
        coords.gridy = 9;
        coords.insets = new Insets(0,0,30,0);
        painelCentral.add(fieldTelefone,coords);
        coords.gridy = 10;
        painelCentral.add(buttonCadastrar,coords);
        coords.gridy = 11;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Excluir Cliente(CPF)"),coords);
        coords.gridy = 12;
        coords.insets = espacamento;
        painelCentral.add(fieldRemover,coords);
        coords.gridy = 13;
        painelCentral.add(buttonRemover,coords);

        add(painelCentral,BorderLayout.CENTER);
        add(barPesquisa,BorderLayout.NORTH);

        setVisible(true);
        menuPesquisa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                FormPesquisaCliente f = new FormPesquisaCliente();
            }
        });
        buttonCadastrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{
                    boolean infoJaExiste = false,menor13Anos = false;
                    for(Cliente c : Cliente.getListaClientes())
                    {
                        if(fieldCpf.getText().equals(c.getCpfCliente()))
                            infoJaExiste = true;
                    }
                    if(Integer.parseInt(fieldIdade.getText())<13)
                    {
                        menor13Anos = true;
                    }

                    if(fieldCpf.getText().equals("")||fieldNome.getText().equals("")||fieldIdade.getText().equals("")||fieldEmail.getText().equals("")||fieldTelefone.getText().equals("")){
                        throw new IllegalArgumentException();
                    }
                    else if(Double.parseDouble(fieldCpf.getText())<=0||Double.parseDouble(fieldTelefone.getText())<=0){
                        throw new IllegalArgumentException();
                    }
                    else if(fieldCpf.getText().length()!=11){
                        throw new LengthException();
                    }
                    else if(infoJaExiste == true)
                    {
                        throw new IllegalArgumentException();
                    }
                    else if(menor13Anos==true)
                    {
                        throw new IllegalArgumentException();
                    }
                    else if(fieldEmail.getText().endsWith("@gmail.com")){
                        Cliente.cadastrar(new Cliente(fieldNome.getText(),Integer.parseInt(fieldIdade.getText()),fieldCpf.getText(),fieldEmail.getText(),fieldTelefone.getText()));
                        fieldNome.setText(null);
                        fieldIdade.setText(null);
                        fieldCpf.setText(null);
                        fieldEmail.setText(null);
                        fieldTelefone.setText(null);
                        JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
                    }
                    else{
                        throw new IllegalArgumentException();
                    }
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null,"Insira valores válidos!");
                }
                catch(LengthException ex){
                    JOptionPane.showMessageDialog(null,"Digite o CPF corretamente!");
                }
            }
        });
        buttonRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean clienteExiste = false;
                for(Cliente c : Cliente.getListaClientes()){
                    if(fieldRemover.getText().equals(c.getCpfCliente()))
                        clienteExiste = true;
                }
                if(clienteExiste == false)
                {
                    JOptionPane.showMessageDialog(null,"Cliente não encontrado!");
                    fieldRemover.setText(null);
                }
                else
                {
                    Cliente.excluir(fieldRemover.getText());
                    fieldRemover.setText(null);
                    JOptionPane.showMessageDialog(null,"Cliente excluído com sucesso!");
                }

            }
        });
    }
}
