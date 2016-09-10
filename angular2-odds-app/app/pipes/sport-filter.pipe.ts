import {Pipe, PipeTransform, Injectable} from '@angular/core';
import {Match} from "../dto/offer";

@Pipe({
    name: 'sportFilter',
    pure: false
})

@Injectable()
export class SportFilterPipe implements PipeTransform {
    transform(matches: Array<Match>, args: any[]): any {
        return matches.filter(
            match => match.competition.sport.name === args[0]);
    }
}