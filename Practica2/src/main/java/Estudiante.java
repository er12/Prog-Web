public class Estudiante{

    private String matricula;
    private String nombre;
    private String apellidos;
    public Estudiante(String matricula, String nombre, String apellidos, String telefono) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    private String telefono;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}