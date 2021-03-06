import {Component, OnInit, OnDestroy} from "@angular/core";
import {Match, Recommendations} from "../../dto/offer";
import {Http, Headers, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Deserializer} from "../../utils/Deserializer";
import {Stomp, Client} from "stompjs";
import {UserService} from "../../services/user.service";
import {AuthService} from "../../services/auth.service";

@Component({
    moduleId: module.id,
    selector: 'odds',
    templateUrl: 'odds.component.html',
    styleUrls: ['odds.component.css']
})
export class OddsComponent implements OnInit, OnDestroy {

    matches: Array<Match> = [];
    matchesBasket: Array<Match> = [];
    private mouseMoveEvents$;
    private clickEvents$;
    private sendRecommendationsEvent$;

    private recommendMatches: Set<number> = new Set<number>();
    private stompClient: Client;
    private isStompClientConnected: boolean = false;

    constructor(private http: Http, private authService: AuthService, private userService: UserService) {

        if(!authService.isAuthorized()) {
            return;
        }

        this.stompClient = Stomp.client('ws://192.168.182.198:61614/stomp/websocket');
        this.stompClient.connect('admin', 'admin',
            () => {
                console.log('Stomp client connected');
                this.isStompClientConnected = true;
            });
    }

    ngOnInit() {
        this.loadOffer();
        this.subscribeUserMonitoring();
    }

    loadOffer() {

        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');

        this.http.get('http://192.168.182.198:8081/offer/2017-08-23', {headers: headers})
            .map((res: Response) => res.json())
            .map((matches: Array<any>) => {
                return Deserializer.deserialize(matches);
            })
            .toPromise()
            .then(loadedMatches => {
                    this.matches = loadedMatches.filter(match => match.competition.sport.name === 'Football');
                    this.matchesBasket = loadedMatches.filter(match => match.competition.sport.name === 'Basketball');
                    console.log(this.matches);
                    console.log(this.matchesBasket);
                    console.log('Offer loaded');
                }
            )
            .catch(err => console.log("Offer NOT LOADED!!! " + err));
    }

    subscribeUserMonitoring() {

        if (!this.authService.isAuthorized()) {
            return;
        }

        this.mouseMoveEvents$ = Observable.fromEvent<MouseEvent>(document, 'mousemove')
            .sampleTime(3000)
            .distinctUntilChanged()
            .subscribe(x => {
                let matchId = x.srcElement.parentElement.dataset['matchId'];
                if (matchId !== undefined) {
                    this.recommendMatches.add(Number.parseInt(matchId));
                }
            });

        this.clickEvents$ = Observable.fromEvent<MouseEvent>(document, 'click')
            .subscribe(x => {
                let matchId = x.srcElement.parentElement.dataset['matchId'];
                if (matchId !== undefined) {
                    this.recommendMatches.add(Number.parseInt(matchId));
                }
            });

        this.sendRecommendationsEvent$ = Observable.timer(1000, 8000)
            .timeInterval()
            .subscribe(x => {

                if (this.recommendMatches.size === 0) {
                    return;
                }

                if(!this.isStompClientConnected) {
                    console.log("NOT CONNECTED");
                    return;
                }

                this.stompClient.send(
                    'aca.recommend.queue',
                    {},
                    JSON.stringify(new Recommendations(this.userService.getUser().id, this.recommendMatches))
                );

                this.recommendMatches.clear();
            });
    }

    unsubscribeUserMonitoring() {

        if (!this.authService.isAuthorized()) {
            return;
        }

        this.mouseMoveEvents$.unsubscribe();
        this.clickEvents$.unsubscribe();
        this.sendRecommendationsEvent$.unsubscribe();
        this.stompClient.disconnect(() => console.log('Stomp client disconnected'));
        this.isStompClientConnected = false;
    }

    ngOnDestroy() {
        this.unsubscribeUserMonitoring();
    }
}