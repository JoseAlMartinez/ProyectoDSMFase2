package sv.edu.udb.guia07app.Modelo;

public class Historial {

    String key;
    private String nombre_producto;
    private String direccion;
    private double precio;
    private String img;

    public Historial() {

    }

    public Historial(String nombre_producto, String direccion, double precio, String img) {
        this.nombre_producto = nombre_producto;
        this.direccion = direccion;
        this.precio = precio;
        this.img = img;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
