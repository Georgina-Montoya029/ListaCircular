
package listacircular;


public class ListaCircular {

   
    public static void main(String[] args) {
        try {
            ListaEnlazadaCircularDoble<String> lista = new ListaEnlazadaCircularDoble<>();
            
            lista.append("Elemento A");
            lista.append("Elemento B");
            lista.append("Elemento C");
            
            System.out.println("Tamaño de la lista: " + lista.size()); 
            lista.irInicio();
            System.out.println("Cursor en inicio: " + lista.getActual()); 
            
            lista.irFinal();
            System.out.println("Cursor en final: " + lista.getActual()); 
            
            lista.irInicio();
            System.out.println("Posicionados en inicio: " + lista.getActual()); 
            
            lista.anterior(); 
            System.out.println("Después de ir al anterior desde el inicio: " + lista.getActual()); 
            
        } catch (ListException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
    
}
