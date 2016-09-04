import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
    selector: 'ticket-viewer',
    templateUrl: 'app/components/ticket-viewer/ticketViewer.component.html',
    styleUrls: ['app/components/ticket-viewer/ticketViewer.component.css']
})
export class TicketViewerComponent implements OnInit {

    private static modalInfo = {'title': "Obavestenje", 'content': "Uspesno ste uplatili tiket"};

    @Output()
    sendMessage: EventEmitter<Object> = new EventEmitter<Object>();

    constructor() { }

    ngOnInit() { }

    onModalMessageSend() {
        console.log(TicketViewerComponent.modalInfo);
        this.sendMessage.emit(TicketViewerComponent.modalInfo);
    }

}