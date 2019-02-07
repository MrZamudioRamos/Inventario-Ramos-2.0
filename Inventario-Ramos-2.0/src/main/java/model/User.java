package model;

public class User {

    private int idusuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private String user;
    private String password;
    private String dni;
    private boolean invent;
    private boolean admin;

    public User() {
    }

    public User(int idusuario, String nombre, String apellido, String telefono, String mail, String user, String password, String dni, boolean invent, boolean admin) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.user = user;
        this.password = password;
        this.dni = dni;
        this.invent = invent;
        this.admin = admin;
    }

    public User(String nombre, String apellido, String telefono, String mail, String user, String password, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.user = user;
        this.password = password;
        this.dni = dni;
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

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public boolean isInvent() {
        return invent;
    }

    public void setInvent(boolean invent) {
        this.invent = invent;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" + "idusuario=" + idusuario + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", mail=" + mail + ", user=" + user + ", password=" + password + ", dni=" + dni + ", invent=" + invent + ", admin=" + admin + '}';
    }



}
