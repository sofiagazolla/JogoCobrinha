O tradicional jogo da cobrinha desenvolvido em Java. 

<img width="909" height="701" alt="Captura de tela de 2025-08-20 19-27-19" src="https://github.com/user-attachments/assets/1e55c45e-2400-49b7-b606-bbeebb76b32f" />

Esse jogo foi desenvolvido utilizando a biblioteca Java Swing para a interface gráfica.
A cobrinha é representada por dois arrays, um que guarda as posições X e o outro que guarda as posições Y da cobra. 
A cabeça é sempre o índice 0 e as suas diferentes direções são representadas por diferentes imagens. Cada segmento do corpo segue a posição do segmento anterior. 
O movimento é sempre contínuo graças ao uso do Timer que é atualizado seguindo a variável delay.

Há uma lógica garantindo que a cobra não possa simplesmente inverter a posição, como isso faria com que ela colidisse consigo mesma. 
Também é verificado se ela colidiu com alguma parte do seu corpo ou com as paredes, pois caso isso ocorra, o método gameover é chamado e o jogo acaba. 

Para jogar, basta:

1. Clonar o repositório
   ```bash
   git clone https://github.com/seu-usuario/jogo-cobrinha-java.git
   ```
2. Compilar o projeto
   ```bash
   javac joguinho/*.java
   ```
3. Executar o jogo
   Execute o jogo:
   ```bash
   java joguinho.Main
   ```

Os arquivos necessários para o jogo são os seguintes, seguindo essa estrutura e tendo essas funções:
```
📁 joguinho
 ├── Jogar.java        # Lógica e renderização do jogo
 ├── Main.java         # Classe principal que inicializa o jogo
 ├── titulo.png        # Imagem do título
 ├── normal.png        # Cabeça da cobra
 ├── direita.png       # Cabeça virada para a direita
 ├── esquerda.png      # Cabeça virada para a esquerda
 ├── pontacabeca.png   # Cabeça para cima
 ├── corpo.png         # Corpo da cobra
 └── morango.png       # Fruta do jogo
```
