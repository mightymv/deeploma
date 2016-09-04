/**
 * var - standardna promenljiva u JS-u, globalno dostupna u funkciji (hoisted)
 * let - promenljiva koja je dostupna samo u bloku gde je deklarisana (not hoisted)
 * const
 */
class HelloWorld {

    public name:string;

    constructor(name:string) {
        this.name = name;
    }

    public printing() {

        var fooVar = "fooVar";
        const fooConst = "fooConst";

        if (true) {

            let boo = "boo Block var";
            var booVar = "boo";
        }

        console.log(fooVar);
        console.log(fooConst);
        // console.log(boo); // error
        console.log(booVar);
    }
}

var aca = new HelloWorld("Hello World from typescript :)");
console.log(aca.name);

aca.printing();
