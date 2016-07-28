var sbs = sbs || {};
sbs.models = sbs.models || {};

sbs.models.EmployeeSearchModel = (function() {
	var EmployeeSearchModel = Backbone.Model.extend({
	
		getParams: function() {
			var params = this.attributes;
			return params;
		}
		
	});
	return EmployeeSearchModel;
}());