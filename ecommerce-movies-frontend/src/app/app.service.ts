/* import { Injectable } from '@angular/core';
import { Frete } from './Frete';
/* import { Http, Response } from '@angular/http'; 
/* import { HttpClient, HttpResponse } from '@angular/common/http'; 
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

@Injectable()
export class FreteService {
    private _Url = 'http://127.0.0.1:8887/frete.json';

    constructor(private _http: HttpClient) { }
    
/*  getFrete(cep: string): Observable<Frete> {
        return this._http.get(this._Url)
            .map((response: Response) => <Frete>response.json())
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError); 
    } s

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
} */