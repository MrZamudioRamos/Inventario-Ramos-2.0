package model;

public class User {

    private int idusuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private String password;
    private String dni;
    private int tipo;

    public User(){}
    
    public User(int idusuario, String nombre, String apellido, String telefono, String mail, String password, String dni, int tipo) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.password = password;
        this.dni = dni;
        this.tipo = tipo;
    }

    public User(String nombre, String apellido, String telefono, String mail, String password, String dni, int tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.password = password;
        this.dni = dni;
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "User{" + "idusuario=" + idusuario + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", mail=" + mail + ", password=" + password + ", dni=" + dni + ", tipo=" + tipo + '}';
    }

}
