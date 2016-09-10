import {Pipe, PipeTransform, Injectable} from "@angular/core";
import {Odd} from "../dto/offer";

@Pipe({
    name: 'gameFilter',
    pure: false
})

@Injectable()
export class GameFilterPipe implements PipeTransform {
    transform(odds: Array<Odd>, arg: any): any {
        return odds.filter(odd => odd.subGame.game.name === arg);
    }
}