import 'dart:html';

num rotatePos = 0;

void main() {
  
  Composite root = new Composite("root");
  root.Add(new Leaf("Leaf A"));
  root.Add(new Leaf("Leaf B"));
  
  Composite comp = new Composite("Composite X");
  comp.Add(new Leaf("Leaf XA"));
  comp.Add(new Leaf("Leaf XB"));
  
  root.Add(comp);
  root.Add(new Leaf("Leaf C"));
  
  // Agregar y eliminar las hojas del patrón de diseño
  Leaf leaf = new Leaf("Leaf D");
  root.Add(leaf);
  root.Remove(leaf);
  
  // Mostrar el arbol de forma recursiva
  root.Display(1);
}

abstract class Component
{
  String name;
  Component(this.name);
  
  void Add(Component c);
  void Remove(Component c);
  void Display(int depth);
}

class Composite extends Component
{
  List<Component> _children = new List<Component>();
  Composite(String name) : super(name);
  
  void Add(Component component)
  {
    _children.add(component);
  }
  
  Remove(Component component)
  {
    var cIndex = _children.indexOf(component);
    _children.removeAt(cIndex);
  }
  
  void Display(int depth)
  {

    print("- ${depth} - ${name}");
    
    // Mostrar los nodos hijo de forma recursiva
    for (Component component in _children)
    {
      component.Display(depth + 2);
    }
  }
}

class Leaf extends Component
{
  Leaf(String name) : super(name);
  
  void Add(Component c)
  {
    print("No se puede agregar una hoja");
  }
  void Remove(Component c)
  { 
    print("No se puede remover la hoja");
  }
  void Display(int depth)
  {
    
    print("- ${depth} - ${name}");
  }
  
}

