import { Component, OnInit, Output, EventEmitter } from '@angular/core';  

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

	@Output() changed = new EventEmitter<string>();
	@Output() keyuped = new EventEmitter<string>();
	constructor() { }  
	ngOnInit() { }
	onChange(status:string){  
		this.changed.emit(status); 
	}
	onKey(owner:string){
		this.keyuped.emit(owner);
	}
}