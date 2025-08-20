package joguinho;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Jogar extends JPanel implements KeyListener, ActionListener {

    Color fundo = new Color(229,107,112); // cor do fundo do jogo

    // Arrays que guardam as posições de cada parte da cobra
    private int[] comprimentoXCobra = new int[750];
    private int[] comprimentoYCobra = new int[750];

    // Todas as direções possíveis para a cobra
    private boolean esquerda = false;
    private boolean direita = false;
    private boolean cima = false;
    private boolean baixo = false;

    // As imagens da cobra, olhando em cada direção
    private ImageIcon cabecaReta;
    private ImageIcon cabecaEsquerda;
    private ImageIcon cabecaDireita;
    private ImageIcon cabecaBaixo;

    private int comprimentoCobra = 3; // Tamanho inicial da cobra


    private Timer timer; // Timer para controlar quando a tela é repintada (a velocidade do jogo)
    private int delay = 100; // O tempo em que a tela é repintada

    private ImageIcon corpo; // A imagem do corpo da cobrinha

    // Pontuação e quantidade de movimentos
    private int movimentos = 0;
    private int pontos = 0;

    private ImageIcon titulo; // A imagem do título
    private boolean isGameOver = false; // Variável que controla se o jogo acabou

    // Todas as posições possíveis da fruta
    private int [] posicaoXFruta = {25, 50, 75, 125, 150, 175,
        200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
        475, 500, 525, 550, 575, 600, 625, 650, 675, 600, 725,
        750, 775, 800, 825, 850};

    private int [] posicaoYFruta = {75, 125, 150, 175,
            200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
            475, 500, 525, 550, 575, 600, 625};

    private ImageIcon fruta; // Imagem do morango

    private Random random = new Random(); // Random é usado para definir as posição da fruta durante o jogo

    // É gerada uma posição inicial aleatória (e posteriormente outras posições aleatórias)
    private int posicaoX = random.nextInt(34);
    private int posicaoY = random.nextInt(22);


    public Jogar(){
        addKeyListener(this); // Adiciona responsabilidade ao teclado
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); // Teclas transversais não tem prioridade (não são reconhecidas a não ser que seja estabelecido)

        // Passa os parâmetros ao timer e o inicia
        timer = new Timer(delay, this);
        timer.start();

    }

    // Método de game over, que para o jogo
    public void gameOver(){
        isGameOver = true;
        timer.stop();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g); // Limpa toda a tela antes de desenhar

        // Posição inicial da cobra
        if (movimentos == 0){
            comprimentoXCobra[0] = 100;
            comprimentoXCobra[1] = 75;
            comprimentoXCobra[2] = 50;

            comprimentoYCobra[0] = 100;
            comprimentoYCobra[1] = 100;
            comprimentoYCobra[2] = 100;
        }

        // Desenha o título
        titulo = new ImageIcon(getClass().getResource("/joguinho/titulo.png"));
        titulo.paintIcon(this, g, 0,0);

        // Desenha a borda do jogo
        g.setColor(Color.DARK_GRAY);
        g.drawRect(0, titulo.getIconHeight(), getWidth() - 1, getHeight() - titulo.getIconHeight() - 25);

        // Preenche o fundo
        g.setColor(fundo);
        g.fillRect(0,titulo.getIconHeight(),getWidth(),getHeight() - titulo.getIconHeight());

        // Renderiza a cobra em sua posição inicial
        cabecaReta = new ImageIcon(getClass().getResource("/joguinho/normal.png"));
        cabecaReta.paintIcon(this, g, comprimentoXCobra[0], comprimentoYCobra[0]);

        for (int i = 0; i < comprimentoCobra; i++){

            // Estabelece as imagens diferentes da cabeça e a imagem do corpo
            if (i==0 && direita){
                cabecaDireita = new ImageIcon(getClass().getResource("/joguinho/direita.png"));
                cabecaDireita.paintIcon(this,g, comprimentoXCobra[i], comprimentoYCobra[i]);
            }
            if (i==0 && esquerda){
                cabecaEsquerda = new ImageIcon(getClass().getResource("/joguinho/esquerda.png"));
                cabecaEsquerda.paintIcon(this,g, comprimentoXCobra[i], comprimentoYCobra[i]);
            }
            if (i==0 && baixo){
                cabecaBaixo = new ImageIcon(getClass().getResource("/joguinho/normal.png"));
                cabecaBaixo.paintIcon(this,g, comprimentoXCobra[i], comprimentoYCobra[i]);
            }
            if (i==0 && cima){
                cabecaReta = new ImageIcon(getClass().getResource("/joguinho/pontacabeca.png"));
                cabecaReta.paintIcon(this,g, comprimentoXCobra[i], comprimentoYCobra[i]);
            }
            if (i!=0){
                corpo= new ImageIcon(getClass().getResource("/joguinho/corpo.png"));
                corpo.paintIcon(this,g, comprimentoXCobra[i], comprimentoYCobra[i]);
            }

            fruta = new ImageIcon(getClass().getResource("/joguinho/morango.png")); // Estabelece a imagem da fruta

            // Verifica se a cobrinha comeu o morango
            if (posicaoXFruta[posicaoX] == comprimentoXCobra[0] && posicaoYFruta[posicaoY] == comprimentoYCobra[0]){
                pontos += 5; // Aumenta os pontos
                comprimentoCobra ++; // Aumenta o comprimento
                // Designa uma nova posição para a fruta
                posicaoX = random.nextInt(32);
                posicaoY = random.nextInt(21);
            }
            fruta.paintIcon(this, g, posicaoXFruta[posicaoX], posicaoYFruta[posicaoY]); // Coloca a imagem do morango na nova posição estabelecida
        }

        // Verifica se a cobra bateu nela mesma e, se sim, chama game over
        for (int i = 1; i < comprimentoCobra; i++){
            if (comprimentoXCobra[i] == comprimentoXCobra[0] && comprimentoYCobra[i] == comprimentoYCobra[0]){
                gameOver();
            }
        }

        // Se game over, mostra a caixa com a mensagem de game over
        if (isGameOver) {
            String linha1 = "Você perdeu!";
            String linha2 = "Pontos: " + pontos;
            String linha3 = "Clique Enter para recomeçar";

            int caixaLargura = 400;
            int caixaAltura = 150;
            int caixaX = 250;
            int caixaY = 265;
            int centroX = caixaX + caixaLargura / 2;

            g.setColor(Color.pink);
            g.fillRoundRect(caixaX, caixaY, caixaLargura, caixaAltura, 30, 30);

            // Linha 1 (derrota)
            g.setColor(Color.red);
            Font fonte1 = new Font("serif", Font.BOLD, 30);
            g.setFont(fonte1);
            FontMetrics fm1 = g.getFontMetrics(fonte1);
            int larguraLinha1 = fm1.stringWidth(linha1);
            g.drawString(linha1, centroX - larguraLinha1 / 2, caixaY + 45);

            // Linha 2 (pontuação)
            Font fonte2 = new Font("serif", Font.BOLD, 28);
            g.setFont(fonte2);
            FontMetrics fm2 = g.getFontMetrics(fonte2);
            int larguraLinha2 = fm2.stringWidth(linha2);
            g.drawString(linha2, centroX - larguraLinha2 / 2, caixaY + 85);

            // Mensagem 3 (instrução)
            g.setColor(Color.white);
            Font fonte3 = new Font("serif", Font.PLAIN, 20);
            g.setFont(fonte3);
            FontMetrics fm3 = g.getFontMetrics(fonte3);
            int larguraLinha3 = fm3.stringWidth(linha3);
            g.drawString(linha3, centroX - larguraLinha3 / 2, caixaY + 120);


            return; // NÃO desenha cobra e fruta
        }

        // Escreve a pontuação
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 14));
        g.drawString("Pontos: " + pontos, 780, 40);

        g.dispose();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Reinicia o jogo com enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            movimentos = 0;
            pontos = 0;
            comprimentoCobra = 3;
            direita = esquerda = cima = baixo = false;
            isGameOver = false;
            timer.start();
            repaint();

        }

        // Todos os movimentos são feitos igual, mudando apenas os lados

        // Direita
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            movimentos++; // Aumenta os movimentos em um
            direita = true; // Vai para a direita

            // Garante que eu não posso virar na direção oposta (por exemplo, não posso ir pra direita imediatamente se eu estiver indo para a esquerda
            if (!esquerda){
                direita = true;
            } else {
                direita = false;
                esquerda = true;
            }

            cima = false;
            baixo = false;

        }

        // Esquerda
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            movimentos++;
            esquerda = true;

            if (!direita){
                esquerda = true;
            } else {
                esquerda = false;
                direita = true;
            }

            cima = false;
            baixo = false;

        }

        // Cima
        if (e.getKeyCode() == KeyEvent.VK_UP){
            movimentos++;
            cima = true;

            if (!baixo){
                cima = true;
            } else {
                cima = false;
                baixo = true;
            }

            esquerda = false;
            direita = false;

        }

        // Baixo
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            movimentos++;
            baixo = true;

            if (!cima){
                baixo = true;
            } else {
                baixo = false;
                cima = true;
            }

            esquerda = false;
            direita = false;

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.restart();

        if (direita){
            //Atualiza a posição y do corpo (que não muda)
            for (int n = comprimentoCobra - 1; n >= 0; n--){
                comprimentoYCobra[n+1] = comprimentoYCobra[n];
            }

            // Atualiza a posição x do corpo
            for (int n = comprimentoCobra; n >= 0; n--){
                if (n == 0){
                    comprimentoXCobra[n] = comprimentoXCobra[n] + 25; // A cabeça vai 25px pra direita
                } else{
                    comprimentoXCobra[n] = comprimentoXCobra[n-1]; // O corpo recebe o valor da parte anterior
                }
                if (comprimentoXCobra[n] > 850){ // Se a cobra bater na parede, chama game over
                    gameOver();
                }
            }

            repaint(); // Redesenha a tela com as novas posições

        }

        /*
            Todas as outras direções são feitas da mesma maneira, mudando apenas
            se mexemos na posição x ou y (pois para cima e baixo mexeremos na
            posição y), para onde a cabeça é desenhada e qual o limite que ativa
            o game over
         */

        if (esquerda){
            for (int n = comprimentoCobra - 1; n >= 0; n--){
                comprimentoYCobra[n+1] = comprimentoYCobra[n];
            }

            for (int n = comprimentoCobra; n >= 0; n--){
                if (n == 0){
                    comprimentoXCobra[n] = comprimentoXCobra[n] - 25;
                } else{
                    comprimentoXCobra[n] = comprimentoXCobra[n-1];
                }
                if (comprimentoXCobra[n] < 20){ // essa parte q faz ela aparecer do outro lado
                    gameOver();
                }
            }

            repaint();

    }

        if (cima){
            for (int n = comprimentoCobra - 1; n >= 0; n--){
                comprimentoXCobra[n+1] = comprimentoXCobra[n];
            }

            for (int n = comprimentoCobra; n >= 0; n--){
                if (n == 0){
                    comprimentoYCobra[n] = comprimentoYCobra[n] - 25;
                } else{
                    comprimentoYCobra[n] = comprimentoYCobra[n-1];
                }
                if (comprimentoYCobra[n] < 80){ // essa parte q faz ela aparecer do outro lado
                    gameOver();
                }
            }

            repaint();
        }

        if (baixo){
            for (int n = comprimentoCobra - 1; n >= 0; n--){
                comprimentoXCobra[n+1] = comprimentoXCobra[n];
            }

            for (int n = comprimentoCobra; n >= 0; n--){
                if (n == 0){
                    comprimentoYCobra[n] = comprimentoYCobra[n] + 25;
                } else{
                    comprimentoYCobra[n] = comprimentoYCobra[n-1];
                }
                if (comprimentoYCobra[n] > 620){ // essa parte q faz ela aparecer do outro lado
                    gameOver();
                }
            }

            repaint();

        }
    }

    // Não são usados mas precisam ser sobrescritos

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}