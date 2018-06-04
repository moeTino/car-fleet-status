/*export class Address {
  constructor(
    public firstname,
    public lastname,
    public address,
    public city,
    public state,
    public postalcode
  ) {}
}
*/

export class Address {
  constructor(
    public firstname?: string,
    public lastname?: string,
    public address?: string,
    public city?: string,
    public state?: string,
    public postalcode?: string
  ) {}
}

/* var Address = /** @class  (function () {
     function Address(firstname string, lastname string, address string, city string, state string, postalcode string) {
         this.firstname = firstname;
         this.lastname = lastname;
		 this.address = address;
		 this.city = city;
		 this.state = state;
		 this.postalcode = postalcode;
		 
	 }
}
*/
