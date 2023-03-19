class People{
  String? name;
  String? craft;

  People(this.name, this.craft);
  People.fromJson(Map<String, dynamic> json){
    name = json['name'];
    craft = json['craft'];
  }

  @override
  String toString() {
    return 'People{name: $name, craft: $craft}';
  }
}