import {Component, OnInit, Input} from '@angular/core';
import {Match} from "../../../dto/offer/offer";

@Component({
    moduleId: module.id,
    selector: 'matches-table',
    templateUrl: 'matchesTable.component.html',
    styleUrls: ['matchesTable.component.css']
})
export class MatchesTableComponent implements OnInit {

    @Input()
    sportName: string;

    @Input()
    matches: Array<Match>;

    constructor() { }

    ngOnInit() { }

}