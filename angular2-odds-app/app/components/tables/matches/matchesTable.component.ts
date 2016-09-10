import {Component, Input} from "@angular/core";
import {Match} from "../../../dto/offer";

@Component({
    moduleId: module.id,
    selector: 'matches-table',
    templateUrl: 'matchesTable.component.html',
    styleUrls: ['matchesTable.component.css']
})
export class MatchesTableComponent {

    @Input()
    sportName: string;

    @Input()
    matches: Array<Match>;

    constructor() { }
}