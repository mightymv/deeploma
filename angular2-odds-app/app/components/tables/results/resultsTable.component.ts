import {Component, Input} from "@angular/core";
import {Match} from "../../../dto/offer/offer";
import {GameFilterPipe} from "../../../pipes/game-filter.pipe";

@Component({
    selector: 'results-table',
    templateUrl: 'app/components/tables/results/resultsTable.component.html',
    styleUrls: ['app/components/tables/results/resultsTable.component.css'],
    pipes: [GameFilterPipe]
})
export class ResultsTableComponent {

    @Input()
    matches: Array<Match>;

    @Input()
    oddFilter: string;

    constructor() { }
}