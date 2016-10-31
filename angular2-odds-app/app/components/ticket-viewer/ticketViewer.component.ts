import {Component, Output, OnInit, OnDestroy} from "@angular/core";
import {TicketService} from "../../services/ticket.service";
import {PayInTicketRow, PayInInfo} from "../../dto/payTicket";
import {MessageModalComponent} from "../messagesModal/messageModal.component";
import {UserService} from "../../services/user.service";

@Component({
    moduleId: module.id,
    selector: 'ticket-viewer',
    templateUrl: 'ticketViewer.component.html',
    styleUrls: ['ticketViewer.component.css']
})
export class TicketViewerComponent implements OnInit, OnDestroy {

    @Output()
    private modalInfo: PayInInfo = new PayInInfo("Uplata tiketa", "message");

    ticketRows: Array<PayInTicketRow> = [];
    totalOdd: number = 1;

    closeButtonActive: boolean = true;

    private ticketChangeEvent$;
    private ticketCleanEvent$;

    constructor(private payTicketService: TicketService, private userService: UserService) {
    }

    ngOnInit() {
        this.ticketChangeEvent$ = this.payTicketService.ticketChangeEvent$.subscribe(ticketRow => this.onTicketChange());
        this.ticketCleanEvent$ = this.payTicketService.ticketCleanEvent$.subscribe(toClean => this.onTicketChange());
    }

    ngOnDestroy() {
        this.ticketChangeEvent$.unsubscribe();
        this.ticketCleanEvent$.unsubscribe();
    }

    onTicketChange(): void {

        this.ticketRows = this.payTicketService.getTicketRows();
        this.recalculateTicket();
    }

    recalculateTicket(): void {
        this.totalOdd = 1;
        this.ticketRows.forEach(ticketRow => this.totalOdd *= ticketRow.odd);
    }

    cleanTicket() {
        this.payTicketService.removeAllTicketRows();
    }

    toggleCloseButton() {
        this.closeButtonActive = !this.closeButtonActive;
    }

    onPayTicket() {

        if(this.userService.getUser().id === null) {

            this.modalInfo.content = "Tiket nije uplacen, niste ulogovani.";
            this.modalInfo.style = "warning";
            return;
        }

        this.payTicketService.payTicket()
            .subscribe(
                res => {
                    console.log("Payin ticket successful :) " + res);
                    this.modalInfo.content = "Uspesno ste uplatili tiket.";
                    this.modalInfo.style = "primary";
                    this.cleanTicket();
                    this.toggleCloseButton();
                },
                err => {
                    console.log("Payin ticket failed !!! " + err);
                    this.modalInfo.content = "Uplata tiketa neuspesna !!!";
                    this.modalInfo.style = "danger";
                },
                () => console.log("Payin ticket completed.")
            );
    }
}