import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private No Sentinela;

    public Deque() {
        n = 0;
        Sentinela = new No();
        Sentinela.prox = Sentinela;
        Sentinela.ant = Sentinela;
    }

    private class No {
        private Item dado;
        private No prox;
        private No ant;
    }

    public void push_front(Item item) {
        No temp = new No();
        temp.dado = item;

        temp.ant = Sentinela;
        temp.prox = Sentinela.prox;

        Sentinela.prox = temp;
        temp.prox.ant = temp;
        ++n;
    }

    public void push_back(Item item) {
        No temp = new No();
        temp.dado = item;

        temp.ant = Sentinela.ant;
        temp.prox = Sentinela;

        Sentinela.ant = temp;
        temp.ant.prox = temp;
        n++;
    }

    public Item pop_front() {
        No temp = Sentinela.prox;
        Item meuDado = temp.dado;

        temp.ant.prox = temp.prox;
        temp.prox.ant = temp.ant;
        --n;
        return meuDado;
    }

    public Item pop_back() {
        No temp = Sentinela.ant;
        Item meuDado = temp.dado;

        temp.ant.prox = temp.prox;
        temp.prox.ant = temp.ant;
        --n;
        return meuDado;
    }

    public No first() {
        if (Sentinela == Sentinela.prox) return null;
        return Sentinela.prox;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public ListIterator<Item> iterator() {
        return new DequeIterator();
    }

    public class DequeIterator implements ListIterator<Item> {
        private No atual = Sentinela.prox;
        private int indice = 0;
        private No acessadoultimo = null;


        public boolean hasNext() {
            return indice < (n);
        }


        public boolean hasPrevious() {
            return indice > 0;
        }


        public int previousIndex() {
            return indice - 1;
        }


        public int nextIndex() {
            return indice;
        }


        public Item next() {
            if (!hasNext()) return null;

            Item meuDado = atual.dado;
            acessadoultimo = atual;
            atual = atual.prox;
            indice++;
            return meuDado;
        }


        public Item previous() {
            if (!hasPrevious()) return null;
            atual = atual.ant;

            Item meuDado = atual.dado;
            acessadoultimo = atual;
            indice--;
            return meuDado;
        }


        public Item get() {
            if (atual == null) throw new IllegalStateException();
            return atual.dado;
        }


        public void set(Item x) {
            if (acessadoultimo == null) throw new IllegalStateException();
            acessadoultimo.dado = x;
        }


        public void remove() {
            if (acessadoultimo == null) throw new IllegalStateException();
            acessadoultimo.ant.prox = acessadoultimo.prox;
            acessadoultimo.prox.ant = acessadoultimo.ant;
            --n;
            if (atual == acessadoultimo) {
                atual = acessadoultimo.prox;
            } else
                indice--;
            acessadoultimo = null;

        }


        public void add(Item x) {
            No temp = new No();
            temp.dado = x;

            temp.prox = atual.prox;
            temp.ant = atual;

            temp.prox.ant = temp;
            atual.prox = temp;
            n++;
        }
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        //add elements 1, ..., n
        StdOut.println(n + " random integers between 0 and 99");
        Deque<Integer> list = new Deque<Integer>();

        // beginning of Deque-4.png
        for(int i = 0; i < n; i++){
            list.push_front(i);
        }
        StdOut.println(list);
        StdOut.println();
        while( !list.isEmpty()){
            StdOut.println(list.pop_front());
        }
        for( int i = 0; i < n; i++){
            list.push_back(i);
        }
        StdOut.println(list);
        StdOut.println();

        ListIterator<Integer> it = list.iterator();
        while(it.hasNext()){
            int x = it.next();
            it.set(x + 1);
        }

        StdOut.println(list);
        StdOut.println();
        while(it.hasPrevious()){
            int x = it.previous();
            it.set(x + x + x);
        }
        StdOut.println(list);
        StdOut.println();
        while(it.hasNext()){
            int x = it.next();
            if(x%2 == 0) it.remove();
        }
//*/]]
        StdOut.println(list);
        StdOut.println();
        while(it.hasPrevious()){
            int x = it.previous();
            it.add(x + x);
        }
        StdOut.println(list);
        StdOut.println();
    }
}
// end of class