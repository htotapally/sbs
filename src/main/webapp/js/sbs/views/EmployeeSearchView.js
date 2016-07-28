var sbs = sbs || {};
sbs.views = sbs.views || {};

sbs.views.EmployeeSearchView = (function() {
	
	   function getColNames() {
	        var colNames = [
				'First Name'
				, 'Last Name'
				, 'Middle Initial'
				, 'Email Address'
				, 'Phone Number'
				, 'Position Category'
				, 'Date Hired'
				, 'Address 1'
				, 'Address 2'
				, 'City'
				, 'State'
				, 'Zip Code'
				, 'Active Flag' 
			];
	    	return colNames;
	    }	    

	    function getColModel() {

	        var colModel = [
			    { name: 'firstName', index: 'firstName', align: 'center', width: 55, sortable: false, hidden: false, editable:false, hidden: false}
			    , { name: 'lastName', index: 'lastName', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'middleInitial', index: 'middleInitial', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'emailAddress', index: 'emailAddress', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'phoneNumber', index: 'phoneNumber', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'position', index: 'position', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'dateHired', index: 'dateHired', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'address1', index: 'address1', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'address2', index: 'address2', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'city', index: 'city', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'state', index: 'state', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'zip', index: 'zip', align: 'center', width: 60, sortable: false, hidden: false}
			    , { name: 'activeFlag', index: 'activeFlag', align: 'center', width: 60, sortable: false, hidden: false}
			];
	    	return colModel;
	    }
		
		function initGrid(employeesTable) {
	        var grid = $('#' + employeesTable);
	        var colNames = getColNames();
	        var colModel = getColModel();
			grid.jqGrid({
				datatype:'local',
				data: new Array(),
				colNames: colNames,
				colModel: colModel,
				rowNum: 10,
				rowList: [10, 20, 300],
				multiselect: false,
				pager: '#pager',
				viewrecords: true,
				gridview: true,
				rownumbers: true,
				height: 400,
				caption: 'Employee Search Results',
				onSelectRow: function(id) {
				},
		        ondblClickRow: function(rowid, ri, ci) {
		        },
		        
				toolbar: [false, "both"],
				editurl: 'clientArray',
	    		grouping: false,
	    		shrinkToFit: false
			});

			grid.setGridWidth(900, false);
			grid.navGrid('#pager', {edit: false, add: false, del: false, search: true, view: true});
	    }

	
	var EmployeeSearchView = Backbone.View.extend({
	
		initialize: function(){
			initGrid('employeesTable');
		}
	
		, model: new sbs.models.EmployeeSearchModel()
		
		, events: {
	        "click #search" : "search"
	        , 'change #lastName': 'handleLastNameChanged'
	    }
		
		, search: function() {
			var method = "GET";					

			var model = this.model;
			var lastName = model.get('lastName');
			var url = 'webapi/employee/' + lastName;
			
		    $.ajax({
		        type: method,
		        url: url,
		        data: {
		        	
		        },
		        success: function(response, textStatus, xhr) {
		        	
		        	var employees = response;
		        	var grid = $('#employeesTable');
		    		grid.jqGrid("clearGridData", true);
		    		
		    		var gridData;
		    		
		    		if(typeof employees !== 'undefined' && null != employees) {
		    			if(employees instanceof Array) {
		    				gridData = employees;
		    			} else {
		    				gridData = new Array();
		    				gridData.push(employees);
		    			}

		    			var rowList = new Array();
		    			
		    			var n = Math.ceil(gridData.length / 10);
		    			for(var i = 0; i < n; i++) {
		    				rowList.push((i + 1) * 10);
		    			}
		    			
		    			grid.jqGrid('setGridParam', {data: gridData});
		    			grid.jqGrid('setGridParam', {rowNum: gridData.length});
		    			grid.jqGrid('setGridParam', {rowList: rowList});
		    			
		    			setTimeout(function() {
		    	    		grid.trigger("reloadGrid", [{current:true}]);
		    			}, 100);					
		    			
		    		}

		        },
		        error: function(xhr, textStatus, errorThrown) {
		        	alert("Failed to insert data");
		        }
		    });
			
		}
		
		, handleLastNameChanged : function(e) {
			var lastName = e.target.value;
			this.model.set({lastName: lastName});
			
		}

	});
	
	return EmployeeSearchView;
	
}());