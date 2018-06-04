import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { map } from 'rxjs/operators';
import { Observable }   from 'rxjs';
import { Car } from '../models/car.model';

@Injectable()
export class CarService {

	protected url : string = 'http://localhost:8099/cars';

	constructor(private http: HttpClient) {}

	ngOnInit() {this.getAll();}

	public getAll(): Observable<Car[]> {
		return this.http.get<Car[]>(this.url);
	}
	
	public getFilteredByStatus(status: string) {
		return this.http.get<Car[]>(this.url+'/connected/'+status);
	}
	
	public getFilteredByOwner(owner: string) {
		return this.http.get<Car[]>(this.url+'/owner/'+owner);
	}
}

/*
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor() { }
}
ngOnInit() {
    this.getAll();
  }
  // Rest Items Service: Read all REST Items
  public getAll() {
    return this.http
	.get<any[]>(this.url)
	.pipe(map(data => data));
  }
*/