
var CategoryManager = {
    // Returns the url of the application server
    basePath: function () { return 'http://localhost:8080/pr25/api'; },
    // Loads the list of parent categories requested from the app server via ajax request.
	loadParentCategories: function () {
		$.ajax({
            url: this.basePath() + '/categories',
            dataType: 'json',
			cache: false,
            success: function (data) {
				$.each(data, function (index, category) {
                    $('<option>').text(category.name)
                    .attr('value', category.id).appendTo($('#ParentCategoryID'));
                })
            },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
        });
    },
	// Loads the list of categories requested from the app server via ajax request
	showCategoriesList: function () {
        $.ajax({
            url: this.basePath() + '/categories',
            cache: false,
            dataType: 'json',
            success: function(list){
				$('#categories').empty();
				$('#categories').append('<tr><th>Name</th><th>Parent</th><th>Select</th></tr>');
				$.each(list, function (index, category) {
					$('#categories tr:last').after('<tr><td>' + category.name + '</td><td>' + category.parent.name + '</td><td><a href=\'javascript:CategoryManager.showCategoryDetails("' + category.id + '")\'>details</a></td></tr>');
				});
			},
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}	
        });
    },
    // Shows a form with category details,
    showCategoryDetails: function (categoryId) {
        if (categoryId == null) return;
        $('#CategoryListPanel').hide();
        $('#RemoveButton').show();
        $.ajax({
            url: this.basePath() + '/categories/' + categoryId,
            cache: false,
            dataType: 'json',
            success: function (category) {
                $('#CategoryDetailsPanel').show();
                $('#CategoryID').val(category.id);
                $('#CategoryName').val(category.name);
                $('#ParentCategoryID').val(category.parent.id);
                $('#CategoryName').focus();
            },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
        });
    },
    // Returns back to the search mode.
    backToList: function () {
        $('#CategoryDetailsPanel').hide();
        $('#CategoryListPanel').show();
        $('#CategoryListPanel').focus();
    },
    
	// Refreshes the list.
    refreshList: function () {
        this.backToList();
        this.showCategoriesList();
    },
    
	// Formats a URI of a category
    createCategoryUrl: function (requestType) {
        if (requestType == 'POST'){
			return this.basePath() + '/categories';
		}
		return this.basePath() + '/categories/' + encodeURIComponent($('#CategoryID').val());
    },
   
    // Creates an object with the properties retrieved from the input fields 
    // of the "CategoryDetails" form.
    collectFieldValues: function () {
		return JSON.stringify({
            "id": $('#CategoryID').val(),
            "name": $('#CategoryName').val(),
            "parent": {id: $('#ParentCategoryID').val()}
        });
    },
    // Deletes a category.
    deleteCategory: function () {
        if (!confirm('Delete?')) return;
        $.ajax({
            url: this.createCategoryUrl('DELETE'),
            type: 'DELETE',
            success: function () {
                CategoryManager.refreshList();
            },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
        });
    },
    // Shows the professor form with blank values.
    newCategory: function () {
        $('#CategoryListPanel').slideUp("slow");
        $('#RemoveButton').hide();
        $('#CategoryDetailsPanel').show();
        $('#CategoryID').attr('value', null);
        $('#CategoryName').attr('value', 'New Category').focus().select();
        $('#ParentCategoryID').attr('value', null);
    },
    
	// Saves a category. If there is no value in the #CategoryID hidden field then
    // a new category is created by "POST" request. Otherwise an existing category
    // is updated with "PUT" request.
    saveCategory: function () {
        if (!confirm('Save?')) return
        var requestType = $('#CategoryID').val() != '' ? 'PUT' : 'POST';
        $.ajax({
            url: this.createCategoryUrl(requestType),
            dataType: 'json',
            type: requestType,
            contentType: "application/json",
            data: this.collectFieldValues(),
            success: function (result) {
                CategoryManager.refreshList();
            },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
        });
    },
};
$(document).ready(function () {
    // pre-populate the drop down lists of parent categories
    CategoryManager.loadParentCategories();
	CategoryManager.showCategoriesList();
	$('#CategoryListPanel').show();
    $('#CategoryDetailsPanel').hide();
	// attach event handlers to buttons
    $('#BackToListButton').click(function (e) {
        e.preventDefault();
        CategoryManager.backToList();
    });
    $('#NewButton').click(function (e) {
        e.preventDefault();
        CategoryManager.newCategory();
    });
    $('#SaveButton').click(function (e) {
        e.preventDefault();
        CategoryManager.saveCategory();
    });
    $('#RemoveButton').click(function (e) {
        e.preventDefault();
        CategoryManager.deleteCategory();
    });
});