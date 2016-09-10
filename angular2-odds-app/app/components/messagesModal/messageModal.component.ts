import {Component, OnInit, Input} from "@angular/core";
import {PayInInfo} from "../../dto/payTicket";

@Component({
    moduleId: module.id,
    selector: 'message-modal',
    templateUrl: 'messageModal.component.html',
    styleUrls: ['messageModal.component.css']
})
export class MessageModalComponent implements OnInit {

    @Input()
    private modalInfo: PayInInfo;

    constructor() {
    }

    ngOnInit() {
    }
}