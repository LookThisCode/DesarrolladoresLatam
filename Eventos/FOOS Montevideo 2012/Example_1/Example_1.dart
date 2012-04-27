#import('dart:html');

class Example_1 {

  Example_1() {
  }

  void run(String mensaje) {
    write(mensaje);
  }

  void write(String message) {
    
    document.query('#status').innerHTML = message;
  }
}

void main() {
  var calculoPerimetro = new Cuadrado(5).perimetro();
  new Example_1().run(calculoPerimetro.toString());
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