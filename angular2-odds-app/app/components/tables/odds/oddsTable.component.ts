import {Component, Input, OnDestroy} from "@angular/core";
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
export class OddsTableComponent implements OnDestroy {

    @Input()
    matches: Array<Match>;

    @Input()
    oddFilter: string;

    payTicketService: PayTicketService;

    activeOddElements: Array<HTMLTableCellElement>;

    constructor(payTicketService: PayTicketService) {

        this.payTicketService = payTicketService;
        this.payTicketService.ticketCleanEvent$.subscribe(toClean => this.onCleanTicket());
        this.activeOddElements = [];
    }

    ngOnDestroy(): any {
        this.payTicketService.ticketCleanEvent$.unsubscribe();
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

            element.classList.remove('success');
            this.activeOddElements = this.activeOddElements.filter(activeElement => activeElement !== element);
            this.payTicketService.removeTicketRow(ticketRow);
        } else {

            element.classList.add('success');
            this.activeOddElements.push(element);
            this.payTicketService.addTicketRow(ticketRow);
        }
    }

    onCleanTicket() {
        this.activeOddElements.forEach(activeOdd => activeOdd.classList.remove('success'));
    }
}