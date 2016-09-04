import {Component, OnInit, Input} from '@angular/core';

@Component({
    selector: 'message-modal',
    templateUrl: 'app/components/messagesModal/messageModal.component.html',
    styleUrls: ['app/components/messagesModal/messageModal.component.css']
})
export class MessageModalComponent implements OnInit {

    @Input
    message = {'title': "Obavestenje", 'content': "TODO"};

    constructor() { }

    ngOnInit() { }
}