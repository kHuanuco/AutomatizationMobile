package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeScreen extends PageObject {


    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/productTV\"]")
    private WebElement tituloApp;

    ////androidx.recyclerview.widget.RecyclerView[@content-desc="Displays all products of catalog"]/android.view.ViewGroup
    @AndroidFindBy(className = "android.widget.TextView")
    private List<WebElement> listaTituloProductos;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Container for fragments\"]/android.view.ViewGroup")
    private WebElement listaProductos;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/plusIV")
    private WebElement buttonUp;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement buttonAgregar;


    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartTV")
    private WebElement carritoAgregado;


    public String getTitulo() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(tituloApp));
        System.out.println(tituloApp.getText());
        return tituloApp.getText();
    }

    public int getCountElements() {
        //System.out.println("El tamano de la lista es: "+listaTituloProductos.size());
        return listaTituloProductos.size();
    }

    public int validarProducto(String nombreProducto) {
        WebElement item;
        boolean existeProducto= false;
        int indice=0;
        int posicionEncontrada=-1;

        System.out.println("el tamano del arreglo es es: "+listaTituloProductos.size());
        while(!existeProducto && indice<listaTituloProductos.size()){
            item= listaTituloProductos.get(indice); //obtiene un webElement de la lista de productos
            String textoProducto= item.getText();
            System.out.println(textoProducto);
            if(textoProducto.contains(nombreProducto)){
                //encontro el producto a agregar y devuelve su posicion
                existeProducto= true;
                posicionEncontrada= indice;
            }
            indice++;
        }
        return posicionEncontrada;
    }

    public void agregoProductoAlCarrito(String nombreProducto, int cantidad) {
        //formamos el nombre de la cadena xpath para buscar la imagen del producto a agregar
        String xpathProductoAgregar= String.format("//android.widget.ImageView[@content-desc='%s']",nombreProducto);
        WebElement productoAgregar = listaProductos.findElement(By.xpath(xpathProductoAgregar));
        //haciendo click en la imagen del producto
        productoAgregar.click();
        //esto me llevara despues a otra pantalla y hacemos una espera de 5 segundo hasta que se cargeu el boton de agrega +
        //ubicar el boton de subir cantidad, por defecto ya tiene 1
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(buttonUp));
        for (int i=0;i<cantidad-1;i++){
            buttonUp.click(); //si la cantidad es 1, ya no entrara, porque por defecto viene 0
        }
        //despues hacemos click en el buton "Add to cart" para agregar dicha cantidad al carrito
        buttonAgregar.click();

    }

    public void validoCarritoAgregado() {
        //se espera unos segundos a que sea visible el carrito moodificado
        //y que tenga el nnumero de la cantidad introducida en la parte superior del icono
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(carritoAgregado));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validoCarritoAgregadoCantidad(int cantidad) {
        //se espera unos segundos a que sea visible el carrito moodificado
        //y que tenga el nnumero de la cantidad introducida en la parte superior del icono
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(carritoAgregado));
        String cantidadString= carritoAgregado.getText();
        System.out.println("La cantidad del carrito es:" +cantidadString);
        int cantidadCarrito = Integer.parseInt(cantidadString);
        //Validas que la cantidad que el carrito tiene en el tope sea igual a la introducida en la prueba
        Assert.assertEquals(cantidad,cantidadCarrito);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
