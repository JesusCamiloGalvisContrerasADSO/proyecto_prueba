
package modelo;

import java.util.Date;

    
public class Usuario {
    
    int idUsuario;
    long documento;
    String contrasena;
    String nombre;
    String apellido;
    long telefono;
    String email;
    Date fechaContra;
    TipoDocum TipoDocum;
    TipoSangre TipoSangre;
    
    int idPerfil;
    int Docid;
    int Sanid;

    public Usuario() {
    }

    public Usuario(int idUsuario, long documento, String contrasena, int idPerfil, String nombre, String apellido, long telefono, String email, Date fechaContra, TipoDocum TipoDocum, TipoSangre TipoSangre, int Docid, int Sanid) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.contrasena = contrasena;
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.fechaContra = fechaContra;
        this.TipoDocum = TipoDocum;
        this.TipoSangre = TipoSangre;
        this.Docid = Docid;
        this.Sanid = Sanid;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
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

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaContra() {
        return fechaContra;
    }

    public void setFechaContra(Date fechaContra) {
        this.fechaContra = fechaContra;
    }

    public TipoDocum getTipoDocum() {
        return TipoDocum;
    }

    public void setTipoDocum(TipoDocum TipoDocum) {
        this.TipoDocum = TipoDocum;
    }

    public TipoSangre getTipoSangre() {
        return TipoSangre;
    }

    public void setTipoSangre(TipoSangre TipoSangre) {
        this.TipoSangre = TipoSangre;
    }

    public int getDocid() {
        return Docid;
    }

    public void setDocid(int Docid) {
        this.Docid = Docid;
    }

    public int getSanid() {
        return Sanid;
    }

    public void setSanid(int Sanid) {
        this.Sanid = Sanid;
    }

   
    
}
