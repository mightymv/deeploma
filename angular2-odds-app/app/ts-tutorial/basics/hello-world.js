var HelloWorld = (function () {
    function HelloWorld(name) {
        this.name = name;
    }
    HelloWorld.prototype.printing = function () {
        var fooVar = "fooVar";
        var fooConst = "fooConst";
        if (true) {
            var boo = "boo Block var";
            var booVar = "boo";
        }
        console.log(fooVar);
        console.log(fooConst);
        // console.log(boo);
        console.log(booVar);
    };
    return HelloWorld;
}());
var aca = new HelloWorld("Hello World from typescript :)");
console.log(aca.name);
aca.printing();
//# sourceMappingURL=hello-world.js.map