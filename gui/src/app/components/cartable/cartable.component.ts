import { Component, OnInit } from '@angular/core';
import { CarService } from '../../services/car.service';
import { Observable } from 'rxjs';
//import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import { Car } from '../../models/car.model';

@Component({
  selector: 'cartable',
  templateUrl: './cartable.component.html',
  styleUrls: ['./cartable.component.css']
})
export class CartableComponent implements OnInit {

	carsDataSource = new CarDataSource(this.carService, '', '');
	displayedColumns = ['registrationNumber', 'vehicleId', 'owner', 'address', 'type', 'capacity', 'connected'];
	constructor(private carService: CarService) { }
  
	ngOnInit() { }
  
	onChanged(status:string){
	
		if(status!=''){
			this.carsDataSource = new CarDataSource(this.carService, status, '');
		}
		else{  
			this.carsDataSource = new CarDataSource(this.carService, '', '');
		}
	}
	
	onKeyuped(owner:string){
	
		if(owner!=''){
			this.carsDataSource = new CarDataSource(this.carService, '', owner);
		}
		else{  
			this.carsDataSource = new CarDataSource(this.carService, '', '');
		}
		
	}
}
export class CarDataSource extends DataSource<any> {
  constructor(private carService: CarService, private status:string, private owner:string) {
    super();
  }
  connect(): Observable<Car[]> {
	if(this.status == '' && this.owner == ''){
		return this.carService.getAll();
	}
	else if(this.status != ''){
		return this.carService.getFilteredByStatus(this.status);
	}
	else if(this.owner != ''){
		return this.carService.getFilteredByOwner(this.owner);
	}
    
  }
  disconnect() {}
}

/*
export class StatusCarDataSource extends DataSource<any> {
  constructor(private carService: CarService, private status:string) {
    super();
  }
  connect(): Observable<Car[]> {
    return this.carService.getFilteredByStatus(status);
  }
  disconnect() {}
}

export class OwnerCarDataSource extends DataSource<any> {
  constructor(private carService: CarService, private owner:string) {
    super();
  }
  connect(): Observable<Car[]> {
    return this.carService.getAll();
  }
  disconnect() {}
}
*/
