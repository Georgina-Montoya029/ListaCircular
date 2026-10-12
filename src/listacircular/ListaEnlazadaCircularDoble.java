
package listacircular;


public class ListaEnlazadaCircularDoble<T> implements IList<T>, Iterable<T> {
    protected NodoDoble<T> inicio;
    protected NodoDoble<T> actual; 
    protected int nElementos;
    
    
    private class NodoDoble<T> {
        private T dato;
        private NodoDoble<T> sig;
        private NodoDoble<T> ant;
            
        public NodoDoble(T dato) {
            this.dato = dato;
            this.sig = this; 
            this.ant = this; 
        }    
    }
    
    public ListaEnlazadaCircularDoble() {
        inicio = null;
        actual = null;
        nElementos = 0;
    }

    @Override
    public void append(T elemento) throws ListException {
        NodoDoble<T> nodoNuevo = new NodoDoble<>(elemento);
        
        if (inicio == null) {
            inicio = nodoNuevo;
            actual = inicio;
        } else {
            NodoDoble<T> fin = inicio.ant; 
            
            fin.sig = nodoNuevo;
            nodoNuevo.ant = fin;
            nodoNuevo.sig = inicio;
            inicio.ant = nodoNuevo; 
        }
        nElementos++;
    }

    @Override
    public void insert(T elemento, int index) throws ListException {
        if (index < 0 || index > nElementos) {
            throw new ListException("Índice fuera de límites.");
        }
        
        if (index == 0) {
            NodoDoble<T> nodoNuevo = new NodoDoble<>(elemento);
            if (inicio == null) {
                inicio = nodoNuevo;
                actual = inicio;
            } else {
                NodoDoble<T> fin = inicio.ant;
                nodoNuevo.sig = inicio;
                nodoNuevo.ant = fin;
                fin.sig = nodoNuevo;
                inicio.ant = nodoNuevo;
                inicio = nodoNuevo;
            }
            nElementos++;
        } else if (index == nElementos) {
            append(elemento);
        } else {
            NodoDoble<T> temp = inicio;
            for (int i = 0; i < index; i++) {
                temp = temp.sig;
            }
            NodoDoble<T> nodoNuevo = new NodoDoble<>(elemento);
            NodoDoble<T> anteriorNodo = temp.ant;
            
            anteriorNodo.sig = nodoNuevo;
            nodoNuevo.ant = anteriorNodo;
            nodoNuevo.sig = temp;
            temp.ant = nodoNuevo;
            nElementos++;
        }
    }

    @Override
    public T remove(int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de límites.");
        }
        
        T datoEliminado;
        if (nElementos == 1) {
            datoEliminado = inicio.dato;
            inicio = null;
            actual = null;
        } else {
            NodoDoble<T> temp = inicio;
            for (int i = 0; i < index; i++) {
                temp = temp.sig;
            }
            datoEliminado = temp.dato;
            NodoDoble<T> anteriorNodo = temp.ant;
            NodoDoble<T> siguienteNodo = temp.sig;
            
            anteriorNodo.sig = siguienteNodo;
            siguienteNodo.ant = anteriorNodo;
            
            if (temp == inicio) {
                inicio = siguienteNodo;
            }
            if (temp == actual) {
                actual = siguienteNodo;
            }
        }
        nElementos--;
        return datoEliminado;
    }

    @Override
    public boolean removeObj(T elemento) throws ListException {
        if (empty()) return false;
        NodoDoble<T> temp = inicio;
        for (int i = 0; i < nElementos; i++) {
            if (temp.dato.equals(elemento)) {
                remove(i);
                return true;
            }
            temp = temp.sig;
        }
        return false;
    }

    @Override
    public int indexOf(T elemento) {
        if (empty()) return -1;
        NodoDoble<T> temp = inicio;
        for (int i = 0; i < nElementos; i++) {
            if (temp.dato.equals(elemento)) {
                return i;
            }
            temp = temp.sig;
        }
        return -1;
    }

    @Override
    public T get(int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de límites.");
        }
        NodoDoble<T> temp = inicio;
        for (int i = 0; i < index; i++) {
            temp = temp.sig;
        }
        return temp.dato;
    }

    @Override
    public void set(T elemento, int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de límites.");
        }
        NodoDoble<T> temp = inicio;
        for (int i = 0; i < index; i++) {
            temp = temp.sig;
        }
        temp.dato = elemento;
    }

    @Override
    public void clear() {
        inicio = null;
        actual = null;
        nElementos = 0;
    }

    @Override
    public boolean empty() {
        return nElementos == 0;
    }

    @Override
    public int size() {
        return nElementos;
    }

    
    public void irInicio() {
        if (inicio != null) {
            actual = inicio;
        }
    }

    public void irFinal() {
        if (inicio != null) {
            actual = inicio.ant;
        }
    }

    public void anterior() {
        if (actual != null) {
            actual = actual.ant; 
        }
    }

    public T getActual() throws ListException {
        if (actual == null) {
            throw new ListException("No hay elemento actual o la lista está vacía.");
        }
        return actual.dato;
    }

    @Override
    public java.util.Iterator<T> Iterator() {
        return iterator();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private NodoDoble<T> cursor = inicio;
            private int contador = 0;

            @Override
            public boolean hasNext() {
                return contador < nElementos;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T dato = cursor.dato;
                cursor = cursor.sig;
                contador++;
                return dato;
            }
        };
    }
}

