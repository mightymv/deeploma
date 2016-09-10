import {Component, Input, OnInit, OnDestroy} from "@angular/core";
import {Match} from "../../../dto/offer";
import {GameFilterPipe} from "../../../pipes/game-filter.pipe";
import {PayInTicketRow} from "../../../dto/payTicket";
import {TicketService} from "../../../services/ticket.service";

@Component({
    moduleId: module.id,
    selector: 'results-table',
    templateUrl: 'oddsTable.component.html',
    styleUrls: ['oddsTable.component.css'],
    pipes: [GameFilterPipe]
})
export class OddsTableComponent implements OnInit, OnDestroy {

    @Input()
    matches: Array<Match>;

    @Input()
    oddFilter: string;

    private activeOddElements: Array<HTMLTableCellElement>;

    private ticketCleanEvent$;

    constructor(private payTicketService: TicketService) {
        this.activeOddElements = [];
    }

    ngOnInit() {
        this.ticketCleanEvent$ = this.payTicketService.ticketCleanEvent$.subscribe(toClean => this.onCleanTicket());
    }

    ngOnDestroy() {
        this.ticketCleanEvent$.unsubscribe();
    }

    onClick(element: HTMLTableCellElement) {

        let ticketRow = new PayInTicketRow(
            Number.parseInt(element.dataset['betOddId']),
            Number.parseInt(element.dataset['matchId']),
            element.dataset['subGameShortName'],
            Number.parseFloat(element.dataset['odd']),
            element.dataset['competitors']
        );

        if (element.classList.contains('success')) {

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