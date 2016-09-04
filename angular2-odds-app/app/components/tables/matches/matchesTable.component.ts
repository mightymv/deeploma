import {Component, OnInit, Input} from '@angular/core';
import {Match} from "../../../dto/offer/offer";

@Component({
    selector: 'matches-table',
    templateUrl: 'app/components/tables/matches/matchesTable.component.html',
    styleUrls: ['app/components/tables/matches/matchesTable.component.css']
})
export class MatchesTableComponent implements OnInit {

    @Input()
    sportName: string;

    @Input()
    matches: Array<Match>;

    constructor() { }

    ngOnInit() { }

}