var sbs = sbs || {};
sbs.views = sbs.views || {};

sbs.views.EmployeeUpdateView = (function() {
	
	var EmployeeUpdateView = sbs.views.EmployeeView.extend({
	
		initialize: function(){
			
		}
	
		, model: new sbs.models.EmployeeUpdateModel()
		
		, events: {
			'click #retrieve' : 'retrieve'
			, 'click #update' : 'update'
	        , 'change #firstName': 'handleFirstNameChanged'	
	        , 'change #lastName': 'handleLastNameChanged'
	        , 'change #middleInitial': 'handleMiddleInitialChanged'
	        , 'change #emailAddress': 'handleEmailAddressChanged'
	        , 'change #phoneNumber': 'handlePhoneNumberChanged'
	        , 'change #position': 'handlePositionChanged'
	        , 'change #dateHired': 'handleDateHiredChanged'
	        , 'change #address1': 'handleAddress1Changed'
	        , 'change #address2': 'handleAddress2Changed'
	        , 'change #city': 'handleCityChanged'
	        , 'change #state': 'handleStateChanged'
	        , 'change #zip': 'handleZipChanged'
	        , 'change #activeFlag': 'handleActiveFlagChanged'
	    }
		
		, retrieve: function() {
			var method = "GET";					

			var model = this.model;
			var lastName = model.get('lastName');
			var url = 'webapi/employee/' + lastName;
			var EmployeeUpdateView = this;
			
		    $.ajax({
		        type: method,
		        url: url,
		        data: {
		        	
		        },
		        success: function(response, textStatus, xhr) {
		        	var employee;
		        	if(response instanceof Array) {
		        		employee = response[0];
		        	} else {
		        		employee = response;        		
		        	}

		        	model.set({firstName : employee.firstName});
		        	model.set({lastName : employee.lastName});
		        	model.set({middleInitial : employee.middleInitial});
		        	model.set({emailAddress : employee.emailAddress});
		        	model.set({phoneNumber : employee.phoneNumber});
		        	model.set({position : employee.position});
		        	model.set({dateHired : employee.dateHired});
		        	model.set({address1 : employee.address1});
		        	model.set({address2 : employee.address2});
		        	model.set({city : employee.city});
		        	model.set({state : employee.state});
		        	model.set({zip : employee.zipCode});
		        	model.set({activeFlag : employee.activeFlag});
		        	
		        	EmployeeUpdateView.render();
		        },
		        error: function(xhr, textStatus, errorThrown) {
		        	alert("Failed to retrieve data based on employee lastname");
		        }
		    });

		}
		
		, update: function() {
			var url = 'webapi/employee';
			var method = "PUT";					

			var model = this.model;
		    $.ajax({
		        type: method,
		        url: url,
		        data: {
					firstName : model.get('firstName')
					, lastName : model.get('lastName')
					, middleInitial : model.get('middleInitial')
					, emailAddress : model.get('emailAddress')
					, phoneNumber : model.get('phoneNumber')
					, firstname : model.get('firstName')
					, position : model.get('position')
					, dateHired : model.get('dateHired')
					, address1 : model.get('address1')
					, address2 : model.get('address2')
					, city : model.get('city')
					, state : model.get('state')
					, zipCode : model.get('zip')
					, activeFlag : model.get('activeFlag')
		        	
		        },
		        success: function(response, textStatus, xhr) {
		        	alert("Created employee successfully");
		        },
		        error: function(xhr, textStatus, errorThrown) {
		        	alert("Failed to insert data");
		        }
		    });			
		}
		
	    , render : function() {
	    	var model = this.model;
	        this.$('#firstName').val(model.get('firstName'));	
		    this.$('#lastName').val(model.get('lastName'));
		    this.$('#middleInitial').val(model.get('middleInitial'));
	        this.$('#emailAddress').val(model.get('emailAddress'));
		    this.$('#phoneNumber').val(model.get('phoneNumber'));
		    this.$('#position').val(model.get('position'));
		    this.$('#dateHired').val(model.get('dateHired'));
		    this.$('#address1').val(model.get('address1'));
		    this.$('#address2').val(model.get('address2'));
		    this.$('#city').val(model.get('city'));
		    this.$('#state').val(model.get('state'));
		    this.$('#zip').val(model.get('zip'));
		    this.$('#activeFlag').val(model.get('activeFlag'));	
	    }


	});
	
	return EmployeeUpdateView;
	
}());