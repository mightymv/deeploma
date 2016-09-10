import {Component, Output, EventEmitter, OnDestroy, ElementRef} from "@angular/core";
import {TicketService} from "../../services/ticket.service";
import {PayInTicketRow} from "../../dto/payTicket";

@Component({
    moduleId: module.id,
    selector: 'ticket-viewer',
    templateUrl: 'ticketViewer.component.html',
    styleUrls: ['ticketViewer.component.css']
})
export class TicketViewerComponent implements OnDestroy {

    private static modalInfo = {'title': "Obavestenje", 'content': "Uspesno ste uplatili tiket"};

    @Output()
    sendMessage: EventEmitter<Object> = new EventEmitter<Object>();

    ticketRows: Array<PayInTicketRow> = [];
    totalOdd: number = 1;

    closeButtonActive:boolean = true;

    constructor(private payTicketService: TicketService) {

        // // TODO read from localStorage
        // if(localStorage.getItem("ticketRows")) {
        //     this.ticketRows = JSON.parse(localStorage.getItem("ticketRows"));
        //     this.recalculateTicket();
        // }

        this.payTicketService.ticketChangeEvent$.subscribe(ticketRow => this.onTicketChange());
        this.payTicketService.ticketCleanEvent$.subscribe(toClean => this.onTicketChange());
    }

    ngOnDestroy(): any {
        this.payTicketService.ticketChangeEvent$.unsubscribe();
        this.payTicketService.ticketCleanEvent$.unsubscribe();
    }

    onModalMessageSend() {
        console.log(TicketViewerComponent.modalInfo);
        this.sendMessage.emit(TicketViewerComponent.modalInfo);
    }

    onTicketChange(): void {

        this.ticketRows = this.payTicketService.getTicketRows();
        // localStorage.setItem("ticketRows", JSON.stringify(this.ticketRows));
        this.recalculateTicket();
    }

    recalculateTicket(): void {
        this.totalOdd = 1;
        this.ticketRows.forEach(ticketRow => this.totalOdd*=ticketRow.odd);
    }

    cleanTicket() {
        this.payTicketService.removeAllTicketRows();
    }

    toggleCloseButton() {
        this.closeButtonActive = !this.closeButtonActive;
    }

    onPayTicket() {
        this.payTicketService.payTicket();
    }
}