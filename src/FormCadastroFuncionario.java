import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FormCadastroFuncionario extends JFrame
{
    public FormCadastroFuncionario()
    {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                FormMenu f = new FormMenu();
            }
        });

        setTitle("Cadastrar funcionário");
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
        JMenuItem menuPesquisa = new JMenuItem("Pesquisar funcionário");
        menu.add(menuPesquisa);

        JTextField fieldNome = new JTextField();
        fieldNome.setPreferredSize(new Dimension(250,25));
        JTextField fieldIdade = new JTextField();
        fieldIdade.setPreferredSize(new Dimension(250,25));
        JTextField fieldCpf = new JTextField();
        fieldCpf.setPreferredSize(new Dimension(250,25));
        JTextField fieldTelefone = new JTextField();
        fieldTelefone.setPreferredSize(new Dimension(250,25));
        JTextField fieldId = new JTextField();
        fieldId.setPreferredSize(new Dimension(250,25));
        JTextField fieldRemover = new JTextField();
        fieldRemover.setPreferredSize(new Dimension(250,25));
        JPasswordField fieldSenha = new JPasswordField();
        fieldSenha.setPreferredSize(new Dimension(250,25));

        JButton buttonCadastrar = new JButton("Cadastrar Funcionário");
        buttonCadastrar.setPreferredSize(new Dimension(250,25));
        JButton buttonRemover = new JButton("Excluir Funcionário");
        buttonRemover.setPreferredSize(new Dimension(250,25));

        JPanel painelCentral = new JPanel(new GridBagLayout());
        coords.gridx = 0;
        coords.gridy = 0;
        painelCentral.add(new JLabel("Nome do Funcionário"),coords);
        coords.gridy = 1;
        coords.insets = espacamento;
        painelCentral.add(fieldNome,coords);
        coords.gridy = 2;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Idade do Funcionário"),coords);
        coords.gridy = 3;
        coords.insets = espacamento;
        painelCentral.add(fieldIdade,coords);
        coords.gridy = 4;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("CPF do Funcionário"),coords);
        coords.gridy = 5;
        coords.insets = espacamento;
        painelCentral.add(fieldCpf,coords);
        coords.gridy = 6;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Telefone do Funcionário"),coords);
        coords.gridy = 7;
        coords.insets = espacamento;
        painelCentral.add(fieldTelefone,coords);
        coords.gridy = 8;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("ID do Funcionário"),coords);
        coords.gridy = 9;
        coords.insets = espacamento;
        painelCentral.add(fieldId,coords);
        coords.gridy = 10;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Senha do Funcionário"),coords);
        coords.gridy = 11;
        coords.insets = new Insets(0,0,30,0);
        painelCentral.add(fieldSenha,coords);
        coords.gridy = 12;
        painelCentral.add(buttonCadastrar,coords);
        coords.gridy = 13;
        coords.insets = semEspacamento;
        painelCentral.add(new JLabel("Excluir Funcionário(ID)"),coords);
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
                FormPesquisaFuncionario f = new FormPesquisaFuncionario();
            }
        });
        buttonCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    boolean infoJaExiste = false,menor18Anos=false;
                    for(Funcionario f : Funcionario.getListaFuncionario())
                    {
                        if(fieldId.getText().equals(String.valueOf(f.getIdFunc()))||fieldId.getText().equals(f.getCpfFunc()))
                        {
                            infoJaExiste = true;
                        }
                    }
                    if(Integer.parseInt(fieldIdade.getText())<18)
                    {
                        menor18Anos = true;
                    }

                    if(fieldId.getText().equals("")||fieldNome.getText().equals("")||fieldIdade.getText().equals("")||fieldCpf.getText().equals("")||fieldTelefone.getText().equals("")||fieldId.getText().equals("")||String.valueOf(fieldSenha.getPassword()).equals("")){
                        throw new IllegalArgumentException();
                    }
                    else if(Double.parseDouble(fieldCpf.getText())<=0||Double.parseDouble(fieldTelefone.getText())<=0||Integer.parseInt(fieldId.getText())<=0){
                        throw new IllegalArgumentException();
                    }
                    else if(fieldCpf.getText().length()!=11){
                        throw new LengthException();
                    }
                    else if(infoJaExiste==true){
                        throw new IllegalArgumentException();
                    }
                    else if(menor18Anos==true){
                        throw new IllegalArgumentException();
                    }
                    else if(String.valueOf(fieldSenha.getPassword()).length()<8)
                    {
                        throw new LengthException();
                    }
                    else{
                        Funcionario.cadastrar(new Funcionario(fieldNome.getText(),Integer.parseInt(fieldIdade.getText()),fieldCpf.getText(),fieldTelefone.getText(),Integer.parseInt(fieldId.getText()),String.valueOf(fieldSenha.getPassword())));
                        fieldNome.setText(null);
                        fieldIdade.setText(null);
                        fieldCpf.setText(null);
                        fieldTelefone.setText(null);
                        fieldId.setText(null);
                        fieldSenha.setText(null);
                        JOptionPane.showMessageDialog(null,"Funcionário cadastrado com sucesso!");
                    }
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null,"Insira valores válidos!");
                }
                catch(LengthException ex){
                    JOptionPane.showMessageDialog(null,"Senha deve ter 8 ou mais caracteres/o CPF é inválido!");
                }
            }
        });
        buttonRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean funcionarioExiste = false;
                for(Funcionario f : Funcionario.getListaFuncionario()){
                    if(fieldRemover.getText().equals(String.valueOf(f.getIdFunc())))
                        funcionarioExiste = true;
                }
                if(funcionarioExiste == false)
                {
                    JOptionPane.showMessageDialog(null,"Funcionário não encontrado!");
                    fieldRemover.setText(null);
                }
                else
                {
                    Funcionario.excluir(Integer.parseInt(fieldRemover.getText()));
                    fieldRemover.setText(null);
                    JOptionPane.showMessageDialog(null,"Funcionário excluído com sucesso!");
                }

            }
        });
    }
}
