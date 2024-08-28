package com.nttdata.stepsdefinitions;

import com.nttdata.steps.HomeSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class HomeStepDef {
    @Steps
    HomeSteps home;

    @Given("estoy en la aplicación de MyDemon")
    public void estoyEnLaAplicaciónDeMyDemon() {

    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        try {
            // Esperar hasta que el elemento sea visible y clickeable
            home.validoHome();
        } catch (Exception e) {
            throw new RuntimeException("Error propio del app al seleccionar ese producto", e);
        }

    }


    @When("agrego {int} del siguiente producto {string}")
    public void agregoDelSiguienteProducto(int cantidad, String nombreProducto) {
        //valido la existencia de un producto, devolviendo el indice, si es -1, no lo encontro
        home.validarProducto(nombreProducto);
        //despues de obtener el indice del producto buscado en la lista
        home.agregoProductoAlCarrito(nombreProducto,cantidad);
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void validoElCarritoDeCompraActualiceCorrectamente() {
        home.validoCarritoAgregado();
    }



    @Then("valido el carrito de compra actualice correctamente con las {int} introducidas")
    public void valido_el_carrito_de_compra_actualice_correctamente_con_las_introducidas(int cantidad) {
        //valido que el carrito tenga la cantidad introducida
        home.validoCarritoAgregadoCantidad(cantidad);
    }

}
