package joguinho;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main (String[] args){

        JFrame tela = new JFrame(); // Cria a janela
        Jogar jogar = new Jogar(); // Cria o painel do jogo

        // Configurações da janela
        tela.setBounds(10,10,905,700);
        tela.setBackground(Color.darkGray); // Não importa, pois vamos desenhar o painel por cima
        tela.setResizable(false);
        tela.setVisible(true);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tela.add(jogar); // Adiciona o painel com o jogo

    }

}