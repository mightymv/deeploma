import {Component, Output, EventEmitter, OnDestroy} from "@angular/core";
import {PayTicketService} from "../../services/pay-ticket.service";
import {TicketRow} from "../../dto/payTicket";

@Component({
    selector: 'ticket-viewer',
    templateUrl: 'app/components/ticket-viewer/ticketViewer.component.html',
    styleUrls: ['app/components/ticket-viewer/ticketViewer.component.css']
})
export class TicketViewerComponent implements OnDestroy {

    private static modalInfo = {'title': "Obavestenje", 'content': "Uspesno ste uplatili tiket"};

    @Output()
    sendMessage: EventEmitter<Object> = new EventEmitter<Object>();

    ticketRows: Array<TicketRow> = [];
    totalOdd: number = 1;

    closeButtonActive:boolean = true;

    constructor(private payTicketService: PayTicketService) {

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
}