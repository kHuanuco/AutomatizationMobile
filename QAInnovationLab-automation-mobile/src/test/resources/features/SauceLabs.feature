
@CompraProductosApp
 Feature: Compra productos App

#   @CompraProductos
#     Scenario: Comprar productos en el app
#     Given estoy en la aplicación de MyDemon
#     And valido que carguen correctamente los productos en la galeria
#     When agrego 2 del siguiente producto "Sauce Labs Backpack"
#     Then valido el carrito de compra actualice correctamente

   @CompraProductosMasivo
   Scenario Outline: Comprar productos de forma masiva en el app
     Given estoy en la aplicación de MyDemon
     And valido que carguen correctamente los productos en la galeria
     When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
     Then valido el carrito de compra actualice correctamente con las <UNIDADES> introducidas
     Examples:
       | PRODUCTO                 | UNIDADES | CANTIDADES | DETALLES                                                                          |
       | Sauce Labs Backpack      | 1        | 1          | Caso exitoso, debe ejecutar bien                                                  |
       | Sauce Labs Bolt T-Shirt  | 1        | 1          | Caso fallido, ya que la cantidad de prueba no se ingresa correctamente al carrito |
       | Sauce Labs Bike Light    | 2        | 2          | Caso fallido, se cierra el app, ya que con ese producto el app no funciona        |
       | Sauce Labs Fleece Jacket | 2        | 3          | Caso exitoso, con 2 unidaes                                                       |