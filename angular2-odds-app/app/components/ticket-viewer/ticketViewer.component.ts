import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {PayTicketService} from "../../services/pay-ticket.service";
import {TicketRow} from "../../dto/payTicket";

@Component({
    selector: 'ticket-viewer',
    templateUrl: 'app/components/ticket-viewer/ticketViewer.component.html',
    styleUrls: ['app/components/ticket-viewer/ticketViewer.component.css']
})
export class TicketViewerComponent implements OnInit {

    private static modalInfo = {'title': "Obavestenje", 'content': "Uspesno ste uplatili tiket"};

    @Output()
    sendMessage: EventEmitter<Object> = new EventEmitter<Object>();

    ticketRows: Array<TicketRow> = [];
    totalOdd: number = 1;

    constructor(private payTicketService: PayTicketService) {

        // TODO read from localStorage

        payTicketService.ticketChangeEvent$.subscribe(ticketRow => this.onTicketChange(ticketRow));
    }

    ngOnInit() { }

    onModalMessageSend() {
        console.log(TicketViewerComponent.modalInfo);
        this.sendMessage.emit(TicketViewerComponent.modalInfo);
    }

    onTicketChange(ticketRow: TicketRow): void {

        // TODO set localStorage

        this.ticketRows = this.payTicketService.getTicketRows();
        this.totalOdd = 1;
        this.recalculateTicket();
    }

    recalculateTicket(): void {
        this.ticketRows.forEach(ticketRow => this.totalOdd*=ticketRow.odd);
    }
}