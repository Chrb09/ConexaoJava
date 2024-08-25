package controle;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import conexao.Conexao;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class FormLogin extends JFrame{
    Conexao con_cliente;
    JLabel titulo, lblUsuario, lblSenha;
    JTextField txtUsuario;
    JPasswordField txtSenha;
    JButton logar;
    
    public FormLogin(){
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("**Login de Acesso**");
        Container tela = getContentPane();
        tela.setLayout(null);
        ImageIcon icone = new ImageIcon("sql.png");
        setIconImage(icone.getImage());
        setResizable(false);
        
        titulo = new JLabel("Acesso ao Sistema");
        lblUsuario = new JLabel("Usuário:");
        lblSenha = new JLabel("Senha:");
        txtUsuario = new JTextField();
        txtSenha = new JPasswordField();
        logar = new JButton("Logar");
        titulo.setFont(new Font("System", Font.BOLD, 24));
        
        titulo.setBounds(85,30,230,25);
        lblUsuario.setBounds(55,100,60,25);
        txtUsuario.setBounds(125,100,190,28);
        lblSenha.setBounds(55,140,60,25);
        txtSenha.setBounds(125,140,190,28);
        logar.setBounds(150,200,100,28);
        
        tela.add(titulo);
        tela.add(lblUsuario);
        tela.add(lblSenha);
        tela.add(txtUsuario);
        tela.add(txtSenha);
        tela.add(logar);
        
        logar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String pesquisa = "select * from tblusuario where usuario like '" + txtUsuario.getText() + "' && senha = " + txtSenha.getText() + "";

                    con_cliente.executaSQL(pesquisa);
                    
                    if(con_cliente.resultset.first()){
                        Formulario mostra = new Formulario();
                        mostra.setVisible(true);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "\n Usuário não cadastrado!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                        con_cliente.desconecta();
                        System.exit(0);
                    }
                }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null, "Os dados digitados não foram localizados! \n"+errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public static void main(String args[]) throws Exception{
        
        String theme= "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        UIManager.setLookAndFeel(theme);
        FormLogin app = new FormLogin();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}