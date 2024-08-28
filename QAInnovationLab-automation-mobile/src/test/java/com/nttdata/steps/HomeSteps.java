package com.nttdata.steps;

import com.nttdata.screens.HomeScreen;
import org.junit.Assert;

public class HomeSteps {
    HomeScreen home;

    public void validoHome() {
        Assert.assertEquals("Products",home.getTitulo());
        //tiene que ser mayor o igual a 1, ya que el primero contiene el titulo Products y los demas son productos en HOME
        Assert.assertTrue(home.getCountElements()>0);
    }

    public void validarProducto(String nombreProducto) {
        int posProducto = home.validarProducto(nombreProducto);
        System.out.println("posicion delproducto: "+posProducto);
        //valida que la posicion no sea -1, ya que si es mayor 0 lo encontro y sigue la prueba, la posicio 0 es del titulo
        Assert.assertTrue("No existe el prducto",posProducto>0);
        //retorna el indice del producto encontrado
    }

    public void agregoProductoAlCarrito(String nombreProducto, int cantidad) {
        home.agregoProductoAlCarrito(nombreProducto,cantidad);
    }

    public void validoCarritoAgregado() {
        home.validoCarritoAgregado();
    }

    public void validoCarritoAgregadoCantidad(int cantidad) {
        home.validoCarritoAgregadoCantidad(cantidad);
    }
}
