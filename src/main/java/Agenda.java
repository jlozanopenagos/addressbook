import java.util.*;

public class Agenda {

    private Map<String, Contacto> listaRegistroContactos = new TreeMap<>();

    private Set<String> gruposAgenda = new TreeSet<>();

    private Map <String, Set<String>> relacionGrupoContacto = new TreeMap<>(); // <String, TreeSet>

    private List<Contacto> historialInsercion = new ArrayList<>();

    /*agregarContacto(Contacto c){

    }

    buscarContacto(String nombre){

    }

    eliminarContacto(String nombre){

    }

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
