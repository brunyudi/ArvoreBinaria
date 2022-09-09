/**
Aluno: Bruno Yudi Mino Okada

Sua  tarefa  será  construir  uma  árvore  binária,  lembrando  que  árvore  binária  são  aquelas  que
possuem  no  máximo  dois  filhos  em  cada  vértice.  Esta  árvore  será  gerada  de  forma  aleatória  e  deve
possuir  um  número  de  vértices  aleatoriamente  selecionado  entre  10  e  26  vértices.  Para  criar  esta
árvore você irá criar um array capaz de armazenar uma quantidade de itens equivalente ao número de
vértices da árvore que será gerada e preencher estes vértices com valores aleatórios entre 1 e 1000.
Este array será usado para montar a árvore a árvore binária.
Você deverá criar uma regra de montagem para varrer o array e colocar seus valores nos
vértices da árvore. Com a árvore binária montada, no terminal, você deverá apresentar as seguintes
saídas:
a) A impressão do array gerado no formato apresentado a seguir para apenas 5 vertices:
        Array Gerado: [1, 12, 45, 3, 6]

b) A impressão da árvore em ASCII usando o modelo a seguir que mostra uma árvore com o
valor 12 na raiz, com dois filhos com os valores 5 e 1. Nesta árvore o vértice de valor 5 possui
dois filhos com valores 11 e 2.
        12
            +5
                +11
                +2
            +1
Observe que os nós estão sendo deslocados horizontalmente por meio do caracter tab
“\t” de forma que os vértices filhos estão deslocados para direita de um tab em relação
ao seu pai. Todas as linhas começam com + para indicar que é um vértice.

c) A  impressão  dos  valores  armazenados  no  vértices  da  árvore  usando  os  algoritmos  de
transversalidade in-order, pre-order e post-order, segundo os modelos a seguir nos quais os
valores utilizados são meramente ilustrativos:
        In-order: {D, B, E, A, F, C, G}
        Pre-order: {A, B, D, E, C, F, G}
        Post-order: {D, E, B, F, G, C, A}

d) Uma lista dos valores armazenados em todas as folhas da árvore gerada segundo o modelo
a seguir, para apenas 5 folhas:
        Folhas: [1, 5, 7, 8, 12]

e) Uma lista do resultado da busca de 10 valores aleatoriamente gerados entre 300 e 575 e o
diagnóstico da existência, ou não, deste número na árvore gerada. Este diagnóstico deve ser
feito com as palavras: presente e ausente, conforme o modelo a seguir para dois valores :
        Valor gerado: 312; Diagnóstico: Presente
        Valor gerado: 542; Diagnóstico: Ausente
*/

import java.util.ArrayList;

class Vertice{
    int valor;
    Vertice direito;
    Vertice esquerdo;
}

class ArvoreBinaria{

    ArrayList<Integer> arrayFolhas = new ArrayList<>();
    ArrayList<Integer> arrayInOrder = new ArrayList<>();
    ArrayList<Integer> arrayPreOrder = new ArrayList<>();
    ArrayList<Integer> arrayPostOrder = new ArrayList<>();


    //Cria um novo vértice para a árvore com o valor dado
    public Vertice novoVertice (int valor){
        Vertice vertice = new Vertice();
        vertice.valor = valor;
        vertice.direito = null;
        vertice.esquerdo = null;
        return vertice;
    }

    //Insere um novo vértice na árvore
    public Vertice inserir(Vertice vertice, int val){
        if (vertice == null){
            return novoVertice(val);
        }
        if (val < vertice.valor){
            vertice.esquerdo = inserir(vertice.esquerdo, val);
        } else if (val > vertice.valor) {
            vertice.direito = inserir(vertice.direito, val);
        }
        return vertice;
    }

    //Deleta um vértice da árvore e arruma o modelo
    public Vertice deletar(Vertice vertice, int val){
        if (vertice == null){
            return null;
        }
        if (val < vertice.valor){
            vertice.esquerdo = deletar(vertice.esquerdo, val);
        } else if (val > vertice.valor){
            vertice.direito = deletar(vertice.direito, val);
        } else {
            if (vertice.esquerdo == null || vertice.direito == null){
                Vertice temp = null;
                temp = vertice.esquerdo == null ? vertice.direito : vertice.esquerdo;

                if (temp == null){
                    return null;
                } else{
                    return vertice;
                }
            } else {
                Vertice sucessor = getSucesssor(vertice);
                vertice.valor = sucessor.valor;
                vertice.direito = deletar(vertice.direito, sucessor.valor);
                return vertice;
            }
        }
        return vertice;
    }

    //Pega o vertice sucessor
    public Vertice getSucesssor (Vertice vertice){
        if (vertice == null){
            return null;
        }
        Vertice temp = vertice.direito;
        while (temp.esquerdo != null){
            temp = temp.esquerdo;
        }
        return temp;
    }

    //Ordena a árvore em InOrder
    public void inOrder(Vertice vertice){
        if (vertice == null){
            return;
        }
        inOrder(vertice.esquerdo);
        arrayInOrder.add(vertice.valor);
        inOrder(vertice.direito);
    }

    //Ordena a árvore em PreOrder
    public void preOrder(Vertice vertice){
        if (vertice == null){
            return;
        }
        arrayPreOrder.add(vertice.valor);
        preOrder(vertice.esquerdo);
        preOrder(vertice.direito);
    }

    //Ordena a árvore em PostOrder
    public void postOrder(Vertice vertice){
        if (vertice == null){
            return;
        }
        postOrder(vertice.esquerdo);
        postOrder(vertice.direito);
        arrayPostOrder.add(vertice.valor);
    }

    //Verifica a existencia de um vértice na árvore
    public boolean buscarVertice(Vertice vertice, int val){
        if (vertice == null){
            return false;
        }
        boolean existe = false;

        while (vertice != null){
            if (val < vertice.valor){
                vertice = vertice.esquerdo;
            } else if (val > vertice.valor) {
                vertice = vertice.direito;
            } else {
                existe = true;
                break;
            }
        }
        return existe;
    }

    //Encontra as folhas da árvore
    public void pegarFolhas(Vertice vertice){
        if (vertice == null){
            return;
        }
        if (vertice.esquerdo == null && vertice.direito == null){
            arrayFolhas.add(vertice.valor);
        }
        if (vertice.esquerdo != null){
            pegarFolhas(vertice.esquerdo);
        }
        if (vertice.direito != null){
            pegarFolhas(vertice.direito);
        }
    }

    //Imprime a árvore em 2D em PreOrder
    public void imprimirArvore(String prefixo, Vertice vertice, boolean filhoDaEsquerda){
        if (vertice != null){
            System.out.println(prefixo + (filhoDaEsquerda ? "\t" : "\t") + "+" + vertice.valor);
            imprimirArvore(prefixo + (filhoDaEsquerda ? "\t" : "\t"), vertice.esquerdo, true);
            imprimirArvore(prefixo + (filhoDaEsquerda ? "\t" : "\t"), vertice.direito, false);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        Vertice raiz = null;
        int minVertices = 10;
        int maxVertices = 26;
        int valorMinVertices = 1;
        int valorMaxVertices = 1000;
        int minBusca = 300;
        int maxBusca = 575;
        ArrayList<Integer> arrayVertices = new ArrayList<>();

        //Gera um número aleatório entre 10 e 26 para a quantidade de vértices
        int quantidadeVertices = (int)Math.floor(Math.random()*(maxVertices-minVertices+1)+minVertices);

        //Gera números aleatórios entre 1 e 1000 para serem os N vértices da árvore
        for (int i = 1; i <= quantidadeVertices; i++){
            int vert = (int)Math.floor(Math.random()*(valorMaxVertices-valorMinVertices+1)+valorMinVertices);
            arrayVertices.add(vert);
        }
        System.out.println("Array Gerado: " + arrayVertices);

        //Adiciona os números gerados anteriormente como os vértices da árvore
        for (int i = 0; i <= arrayVertices.size()-1; i++){
            raiz = arvoreBinaria.inserir(raiz, arrayVertices.get(i));
        }

        //Imprime a árvore em 2D em PreOrder e com os vértices filhos espaçados com um tab(\t) do vértice raiz
        arvoreBinaria.imprimirArvore("",raiz,false);

        //Ordena a árvore em InOrder e imprime
        arvoreBinaria.inOrder(raiz);
        System.out.println("In-order: " + arvoreBinaria.arrayInOrder.toString().replace("[","{").replace("]","}"));

        //Ordena a árvore em PreOrder e imprime
        arvoreBinaria.preOrder(raiz);
        System.out.println("Pre-order: " + arvoreBinaria.arrayPreOrder.toString().replace("[","{").replace("]","}"));

        //Ordena a árvore em PostOrder e imprime
        arvoreBinaria.postOrder(raiz);
        System.out.println("Post-order: " + arvoreBinaria.arrayPostOrder.toString().replace("[","{").replace("]","}"));

        //Encontra os vértices folha da árvore e imprime
        arvoreBinaria.pegarFolhas(raiz);
        System.out.println("Folhas: " + arvoreBinaria.arrayFolhas);

        //Gera 10 valores aleatórios entre 300 e 575 e verifica se está presente ou não na árvore
        for (int i = 1; i <= 10; i++){
            int valBusca = (int)Math.floor(Math.random()*(maxBusca-minBusca+1)+minBusca);
            boolean diagnostico = arvoreBinaria.buscarVertice(raiz, valBusca);
            if (diagnostico == true){
                System.out.println("Valor gerado: " + valBusca + "; Diagnostico: Presente");
            } else {
                System.out.println("Valor gerado: " + valBusca + "; Diagnostico: Ausente");
            }
        }
    }
}
