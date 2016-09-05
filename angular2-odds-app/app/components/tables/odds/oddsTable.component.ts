import {Component, Input} from "@angular/core";
import {Match} from "../../../dto/offer/offer";
import {GameFilterPipe} from "../../../pipes/game-filter.pipe";
import {TicketRow} from "../../../dto/payTicket";
import {PayTicketService} from "../../../services/pay-ticket.service";

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

    payTicketService: PayTicketService

    constructor(payTicketService: PayTicketService) {
        this.payTicketService = payTicketService;
    }

    onClick(element: HTMLTableCellElement) {

        let ticketRow = new TicketRow(
            Number.parseInt(element.dataset['betOddId']),
            Number.parseInt(element.dataset['matchId']),
            element.dataset['subGameShortName'],
            Number.parseFloat(element.dataset['odd']),
            element.dataset['competitors']
        );

        if(element.classList.contains('success')) {

            element.classList.remove('success')
            this.payTicketService.removeTicketRow(ticketRow);
        } else {

            element.classList.add('success')
            this.payTicketService.addTicketRow(ticketRow);
        }
    }
}