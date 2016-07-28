var sbs = sbs || {};
sbs.models = sbs.models || {};

sbs.models.EmployeeModel = (function() {
	var EmployeeModel = Backbone.Model.extend({
	
		getParams: function() {
			var params = this.attributes;
			return params;
		}
		
	});
	return EmployeeModel;
}());