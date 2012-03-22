#import('dart:html');

class Example_2 {

  Example_2() {
  }

  void run(String mensaje) {
    write(mensaje);
  }

  void write(String message) {
    document.query('#status').innerHTML = message;
  }
}

void main() {
  InputElement accion = document.query("#btn");
  accion.on.click.add((event) => calcular(), true);
}

void calcular()
{
  InputElement texto = document.query("#ca");
  
  var calculoPerimetro = new Cuadrado(Math.parseInt(texto.value)).perimetro();
  new Example_2().run(calculoPerimetro.toString());
}
  

//interface
interface Figura
{
 perimetro(); 
}

//Clase que implementa la interface
class Rectangulo implements Figura
{
  final num alto, ancho;
  
  Rectangulo(num this.alto, num this.ancho);
  perimetro() => 2*alto + 2*ancho;
}

//Clase que hereda de Rectangulo
class Cuadrado extends Rectangulo
{
  Cuadrado(num medida) : super(medida,medida);
}