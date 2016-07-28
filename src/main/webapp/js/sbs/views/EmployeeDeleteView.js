var sbs = sbs || {};
sbs.views = sbs.views || {};

sbs.views.EmployeeDeleteView = (function() {
	
	var EmployeeDeleteView = sbs.views.EmployeeSearchView.extend({
	
		model: new sbs.models.EmployeeDeleteModel()
		
		, events: {
	        "click #delete" : "deleteEmployee"
	        , 'change #lastName': 'handleLastNameChanged'
	    }
		
		, deleteEmployee: function() {
			var method = "DELETE";					

			var model = this.model;
			var lastName = model.get('lastName');
			var url = 'webapi/employee/' + lastName;
			
		    $.ajax({
		        type: method,
		        url: url,
		        data: {
		        	
		        },
		        success: function(response, textStatus, xhr) {
		        	alert("Employees were successfully");
		        },
		        error: function(xhr, textStatus, errorThrown) {
		        	alert("Failed to delete data");
		        }
		    });
			
		}

	});
	
	return EmployeeDeleteView;
	
}());