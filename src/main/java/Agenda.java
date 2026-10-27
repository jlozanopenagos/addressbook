import java.util.*;

public class Agenda {

    private Map<String, Contacto> listaRegistroContactos; //Carlos, Ana, Pedro....

    private Set<String> gruposAgenda; // Familia, Amigos, Trabajo...

    private Map <String, Set<String>> relacionGrupoContacto; // <String, TreeSet> / Carlos, trabajo / Ana, Familia

    private List<Contacto> historialInsercion;  // En que orden se agregaron

    public Agenda() {
        listaRegistroContactos = new TreeMap<>();
        gruposAgenda = new TreeSet<>();
        relacionGrupoContacto = new TreeMap<>();
        historialInsercion = new ArrayList<>();
    }

    public void agregarContacto(Contacto c){
        if (c == null || c.getName() == null || c.getName().isBlank()) {
            System.out.println("El contacto no puede ser null o estar en blanco!");
            return;
        }

        if(listaRegistroContactos.containsKey(c.getName()) ){
            System.out.println("Ese contacto ya existe!");
            return;
        }

        listaRegistroContactos.put(c.getName(),c);
    }


    public Contacto buscarContacto(String nombre){
        Contacto contacto = listaRegistroContactos.get(nombre);

        if (contacto == null) {
            System.out.println("Contacto no encontrado!");
            return null;
        }

        System.out.println("Contacto encontrado!");
        return contacto;
    }

    public boolean eliminarContacto(String nombre){
        if(nombre == null || nombre.isBlank()){
            return false;
        }
        Contacto contacto = listaRegistroContactos.get(nombre);

        if (contacto == null) {
            System.out.println("No se puede borrar un contacto inexistente!");
            return false;
        }

        System.out.println("Contacto encontrado");

        for (Set<String> grupo : relacionGrupoContacto.values()) {
            grupo.remove(nombre);
        }

        historialInsercion.remove(contacto);
        listaRegistroContactos.remove(nombre, contacto);

        return true;
    }

    /*
C. ¿Qué pasa con los grupos?
Esta es la parte más interesante del ejercicio.
    *
    * */

    /*
    listarContactos(){

    }

    listarHistorial(){

    }

    agregarGrupo(String nombreGrupo){

    }

    agregarContactoAGrupo(String nombreContacto, String nombreGrupo){

    }

    listarGrupos(){

    }

    mostrarContactosPorGrupo(String nombreGrupo){

    }*/
}
