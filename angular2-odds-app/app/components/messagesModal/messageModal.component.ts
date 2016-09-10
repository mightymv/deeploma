import {Component, OnInit, Input} from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'message-modal',
    templateUrl: 'messageModal.component.html',
    styleUrls: ['messageModal.component.css']
})
export class MessageModalComponent implements OnInit {

    message = {'title': "Obavestenje", 'content': "TODO"};

    constructor() { }

    ngOnInit() { }

    onMessageReceived(messageInfo) {
        console.log("YYY");
        this.message = messageInfo;
    }
}