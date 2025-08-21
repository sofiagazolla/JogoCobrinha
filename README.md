# O Tradicional Jogo da Cobrinha em Java

<p align="center">
  <img src="https://github.com/user-attachments/assets/27f39673-72ab-42da-a114-0224122d17d2" alt="gifjogocobrinha" width="600">
</p>


---

Esse jogo foi desenvolvido utilizando a **biblioteca Java Swing** para a interface gráfica.
A cobrinha é representada por **dois arrays**, um que guarda as posições X e o outro que guarda as posições Y da cobra.
A **cabeça é sempre o índice 0** e as suas diferentes direções são representadas por diferentes imagens. Cada segmento do corpo segue a posição do segmento anterior.
O movimento é sempre contínuo graças ao uso do **Timer**, que é atualizado seguindo a variável `delay`.

Há uma lógica garantindo que a cobra **não possa simplesmente inverter a posição**, como isso faria com que ela colidisse consigo mesma.
Também é verificado se ela colidiu com alguma parte do seu corpo ou com as paredes, pois caso isso ocorra, o método `gameOver` é chamado e o jogo acaba.

---

## Como jogar

1. **Clonar o repositório**

```bash
git clone https://github.com/seu-usuario/jogo-cobrinha-java.git
```

2. **Compilar o projeto**

```bash
javac joguinho/*.java
```

3. **Executar o jogo**

```bash
java joguinho.Main
```

---

## Arquivos do jogo

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
