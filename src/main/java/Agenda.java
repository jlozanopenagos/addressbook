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

        String nombre = c.getName().trim();

        if(listaRegistroContactos.containsKey(nombre) ){
            System.out.println("Ese contacto ya existe!");
            return;
        }

        listaRegistroContactos.put(nombre, c);
        historialInsercion.add(c); // tambien se agrega al historial de contactos
    }

    public Contacto buscarContacto(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            System.out.println("Debes ingresar un nombre válido.");
            return null;
        }

        nombre = nombre.trim();
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


    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {

        // 1. Validar que los nombres no sean nulos ni estén vacíos
        if (nombreContacto == null || nombreContacto.isBlank()
                || nombreGrupo == null || nombreGrupo.isBlank()) {
            System.out.println("El contacto y el grupo deben tener nombres válidos.");
            return;
        }

        // 2. Normalizar los nombres según las reglas de la agenda
        nombreContacto = nombreContacto.trim();
        nombreGrupo = nombreGrupo.trim().toLowerCase(Locale.ROOT);

        // 3. Comprobar que el contacto exista
        if (!listaRegistroContactos.containsKey(nombreContacto)) {
            System.out.println("El contacto no existe!");
            return;
        }

        // 4. Comprobar que el grupo exista
        if (!gruposAgenda.contains(nombreGrupo)) {
            System.out.println("El grupo no existe!");
            return;
        }

        // 5. Obtener el conjunto de contactos asociado al grupo
        Set<String> contactosDelGrupo = relacionGrupoContacto.get(nombreGrupo);

        // 6. Si el grupo todavía no tiene un conjunto, crearlo
        if (contactosDelGrupo == null) {
            contactosDelGrupo = new TreeSet<>();
        }

        // 7. Intentar agregar el contacto al conjunto
        if (!contactosDelGrupo.add(nombreContacto)) {
            System.out.printf("El contacto %s ya pertenece al grupo %s.%n",
                    nombreContacto, nombreGrupo);
            return;
        }

        // 8. Guardar el conjunto asociado al nombre del grupo
        relacionGrupoContacto.put(nombreGrupo, contactosDelGrupo);

        System.out.printf("El contacto %s se agregó al grupo %s.%n",
                nombreContacto, nombreGrupo);
    }


    public void listarGrupos() {
        if (gruposAgenda.isEmpty()) {
            System.out.println("No hay grupos registrados en la agenda.");
            return;
        }

        System.out.println("----------------------------------------");
        System.out.println("           GRUPOS DE LA AGENDA");
        System.out.println("----------------------------------------");

        for (String grupo : gruposAgenda) {
            System.out.println("- " + grupo);
        }
    }



    public void mostrarContactosPorGrupo(String nombreGrupo) {

        if (nombreGrupo == null || nombreGrupo.isBlank()) {
            System.out.println("El nombre del grupo no puede estar vacío.");
            return;
        }

        nombreGrupo = nombreGrupo.trim().toLowerCase(Locale.ROOT);

        if (!gruposAgenda.contains(nombreGrupo)) {
            System.out.println("El grupo no existe en la agenda.");
            return;
        }

        Set<String> contactosDelGrupo = relacionGrupoContacto.get(nombreGrupo);

        if (contactosDelGrupo == null || contactosDelGrupo.isEmpty()) {
            System.out.printf("El grupo %s no tiene contactos registrados.%n",
                    nombreGrupo);
            return;
        }

        System.out.println("----------------------------------------");
        System.out.printf("     CONTACTOS DEL GRUPO: %s%n",
                nombreGrupo.toUpperCase(Locale.ROOT));
        System.out.println("----------------------------------------");

        for (String nombreContacto : contactosDelGrupo) {
            Contacto contacto = listaRegistroContactos.get(nombreContacto);

            if (contacto != null) {
                System.out.println(contacto);
            }
        }
    }

}
