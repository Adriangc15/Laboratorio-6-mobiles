package example.abhiandriod.tablelayoutexample.Model;

import java.io.Serializable;

public class Formulario implements Serializable {

    private String nombre;
    private String apellido;
    private String direccion;
    private String segundaDireccin;
    private String provincia;
    private String ciudad;
    private String zip;
    private String pais;
    private String codigoArea;
    private int numero;
    private String fecha;
    private String formID;
    private String puesto;
    private String correo;

    public Formulario() {
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.segundaDireccin = "";
        this.provincia = "";
        this.ciudad = "";
        this.zip = "";
        this.pais = "";
        this.codigoArea = "";
        this.numero = 0;
        this.fecha = "";
        this.formID = "";
        this.puesto = "";
        this.correo = "";
    }

    public Formulario(String nombre, String apellido, String direccion, String segundaDireccin,
                      String provincia, String ciudad, String zip, String pais, String codigoArea,
                      int numero, String fecha, String id, String puesto, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.segundaDireccin = segundaDireccin;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.zip = zip;
        this.pais = pais;
        this.codigoArea = codigoArea;
        this.numero = numero;
        this.fecha = fecha;
        this.formID = id;
        this.puesto = puesto;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFormID() {
        return formID;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getId() {
        return formID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSegundaDireccin() {
        return segundaDireccin;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getZip() {
        return zip;
    }

    public String getPais() {
        return pais;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public int getNumero() {
        return numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setId(String id) {
        this.formID = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSegundaDireccin(String segundaDireccin) {
        this.segundaDireccin = segundaDireccin;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
