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

    public void listarContactos(){
        if(listaRegistroContactos.isEmpty()){
            System.out.println("No hay contactos que listar!");
            return;
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("             LISTA DE CONTACTOS");
        System.out.println("-----------------------------------------------------");

        for (Map.Entry<String, Contacto> entrada : listaRegistroContactos.entrySet()) {
            System.out.println(entrada.getKey());
            System.out.println(entrada.getValue());
        }
    }


    public void listarHistorial(){
        if (historialInsercion.isEmpty()){
            System.out.println("No se han agregado contactos a la agenda!");
            return;
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("             HISTORIAL DE CONTACTOS");
        System.out.println("-----------------------------------------------------");
        for(Contacto contacto:historialInsercion){
            System.out.println(contacto);
        }

    }

    public void agregarGrupo(String nombreGrupo){
        if(nombreGrupo == null || nombreGrupo.isBlank()){
            System.out.println("El grupo no puede ser un string vacio o nulo!");
            return;
        }
        String inputFormatted = nombreGrupo.trim().toLowerCase(Locale.ROOT);

        if(!gruposAgenda.add(inputFormatted)){
            System.out.printf("La categoría %s no se agregó exitosamente.%n",inputFormatted);
        } else{
            System.out.printf("La categoría %s se agregó exitosamente.%n",inputFormatted);
        }
    }
    /*
    agregarContactoAGrupo(String nombreContacto, String nombreGrupo){

    }

    listarGrupos(){

    }

    mostrarContactosPorGrupo(String nombreGrupo){

    }*/
}
