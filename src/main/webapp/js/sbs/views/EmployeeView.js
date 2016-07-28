var sbs = sbs || {};
sbs.views = sbs.views || {};

sbs.views.EmployeeView = (function() {
	
	var EmployeeView = Backbone.View.extend({
	
		initialize: function(){
			
		}
	
		, model: new sbs.models.EmployeeModel()
		
		, events: {
	        "click #create" : "create"
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
		
		, create: function() {
			var url = 'webapi/employee';
			var method = "POST";					

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
		
		, handleFirstNameChanged: function(e) {
			var firstName = e.target.value;
			this.model.set({firstName: firstName});
		}
		
		, handleLastNameChanged : function(e) {
			var lastName = e.target.value;
			this.model.set({lastName: lastName});
			
		}

		, handleMiddleInitialChanged: function(e) {
			var middleInitial = e.target.value;
			this.model.set({middleInitial: middleInitial});
			
		}

		, handleEmailAddressChanged: function(e) {
			var emailAddress = e.target.value;
			this.model.set({emailAddress: emailAddress});
			
		}
		
		, handlePhoneNumberChanged: function(e) {
			var phoneNumber = e.target.value;
			this.model.set({phoneNumber: phoneNumber});
			
		}
		
		, handlePositionChanged: function(e) {
			var position = e.target.value;
			this.model.set({position: position});
			
		}
		
		, handleDateHiredChanged: function(e) {
			var dateHired = e.target.value;
			this.model.set({dateHired: dateHired});
			
		}
		
		, handleAddress1Changed: function(e) {
			var address1 = e.target.value;
			this.model.set({address1: address1});
			
		}
		
		, handleAddress2Changed: function(e) {
			var address2 = e.target.value;
			this.model.set({address2: address2});
			
		}

		, handleCityChanged: function(e) {
			var city = e.target.value;
			this.model.set({city: city});
			
		}
		
		, handleStateChanged: function(e) {
			var state = e.target.value;
			this.model.set({state: state});
			
		}
		
		, handleZipChanged: function(e) {
			var zip = e.target.value;
			this.model.set({zip: zip});
			
		}
		
		, handleActiveFlagChanged: function(e) {
			var activeFlag = e.target.value;
			if(activeFlag === 'on') {
				this.model.set({activeFlag: 'Active'});
			} else {
				this.model.set({activeFlag: 'Inactive'});
			}
		}

	});
	
	return EmployeeView;
	
}());