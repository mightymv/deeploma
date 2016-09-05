import {Component, Input} from "@angular/core";
import {Match} from "../../../dto/offer/offer";
import {GameFilterPipe} from "../../../pipes/game-filter.pipe";
import {TicketRow} from "../../../dto/payTicket";

@Component({
    selector: 'results-table',
    templateUrl: 'app/components/tables/odds/oddsTable.component.html',
    styleUrls: ['app/components/tables/odds/oddsTable.component.css'],
    pipes: [GameFilterPipe]
})
export class OddsTableComponent {

    @Input()
    matches: Array<Match>;

    @Input()
    oddFilter: string;

    constructor() { }

    onClick(element: HTMLTableCellElement) {

        let ticketRow = new TicketRow(
            Number.parseInt(element.dataset['betOddId']),
            Number.parseInt(element.dataset['matchId']),
            element.dataset['subGameShortName'],
            Number.parseInt(element.dataset['odd'])
        );

        if(element.classList.contains('success')) {

            element.classList.remove('success')
            // remove from list
        } else {

            element.classList.add('success')
            // add to list
        }

        console.log("XXX " + ticketRow.toString());
    }
}