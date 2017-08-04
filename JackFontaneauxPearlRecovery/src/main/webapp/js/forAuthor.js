
$(document).ready(function () {
    var onBlog;
    $('#page-categories-wrapper').hide();
    $('#post-categories-wrapper').hide();
    $('#lat-long-row').hide();
    $('#expire-label').hide();
    $('#expire-input').hide();
    $('#post-label').hide();
    $('#post-input').hide();
    $('#image-preview-row').hide();
    $('#image-page-row').hide();
    $('#image-header-row').hide();
    $('#tags-row').hide();
    $('#author-row').hide();
    $('#title-row').hide();
    $('#synopsis-row').hide();
    $('#body-row').hide();
    $('#complete-row').hide();
    $('#button-row').hide();
  

    loadCategories();


    $('#submitButton').on('click', validateCategories);
    
    
    $('#add-category').on('click', function (event) {

        //var hasValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

        //if(hasValidationErrors){
        //	return false;
        //}
        
        var categoryInputField = $('#category-add').val();
        var categoryInput = $.trim(categoryInputField);

        if (categoryInput === '' || categoryInput.length === 0) {
            $('#category-add').attr("placeholder", "Please insert a new category.");
        } else {

        

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/JackFontaneauxPearlRecovery/create/category',
            data: JSON.stringify({
                categoryName: $('#category-add').val()
            }),
            headers: {
//                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
//            'dataType': 'json',
            success: function () {
                $('#errorMessagesCreate').empty();
                $('#category-add').val('');
                loadCategories();
               
                

            },
            error: function () {
                $('#errorMessagesCreate').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
            }
        });

    }

    });
    
    
    
    
  


});




function loadCategories() {
    clearPostCatDiv();
    var content = $('#post-categories-display');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/JackFontaneauxPearlRecovery/author/categories',
        success: function (categoryArray) {
            $('#errorMessages').empty();
            $.each(categoryArray, function (index, category) {

                var type = category.categoryName;

                var categoryId = category.postCategoryId;

                var categoryAdd = '<label class="col-sm-6 control-label" style="text-align: left; color: black;">';
                categoryAdd += '<input class="col-sm-4" type="checkbox" name="category-type-post" id="post-category-click" value="'
                        + categoryId + '">';
                categoryAdd += type + '</label>';


                content.append(categoryAdd);
            });
        },
        error: function () {
            $('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
        }
    });
}




function clearPostCatDiv() {
    $('#post-categories-display').html('');
}





function showBlog() {
    
    $('#page-categories-wrapper').hide();
    $('#post-categories-wrapper').show();
    $('#lat-long-row').show();
    $('#image-preview-row').show();
    $('#image-page-row').hide();
    $('#image-header-row').show();
    $('#tags-row').show();
    $('#author-row').show();
    $('#title-row').show();
    $('#synopsis-row').show();
    $('#expire-label').show();
    $('#expire-input').show();
    $('#post-label').show();
    $('#post-input').show();
    $('#body-row').show();
    $('#complete-row').show();
    $('#button-row').show();
    controlRequired();
};

function showPage() {
    $('#post-categories-wrapper').hide();
    $('#lat-long-row').hide();
    $('#expire-label').hide();
    $('#expire-input').hide();
    $('#image-preview-row').hide();
    $('#image-header-row').hide();
    $('#image-page-row').show();
    $('#page-categories-wrapper').show();
    $('#tags-row').show();
    
    $('#page-categories-wrapper').show();
    $('#post-categories-wrapper').hide();
    $('#lat-long-row').hide();
    $('#expire-label').hide();
    $('#expire-input').hide();
    $('#post-label').show();
    $('#post-input').show();
    $('#image-preview-row').hide();
    $('#image-page-row').show();
    $('#image-header-row').hide();
    $('#tags-row').hide();
    $('#author-row').show();
    $('#title-row').show();
    $('#synopsis-row').hide();
    $('#body-row').show();
    $('#complete-row').show();
    $('#button-row').show();
    controlRequired();
};


function controlRequired() {
    //$('.form-group:hidden').removeAttr('required');
    $('input:hidden').removeAttr('required');
    $('input:hidden').attr('novalidate', 'novalidate');
    $('select:hidden').removeAttr('required');
    $('select:hidden').attr('novalidate', 'novalidate');
    
    //$('.form-group:visible').attr('required', 'required');
    $('input:visible').attr('required');
    $('input:visible').removeAttr('novalidate');
    $('select:visible').attr('required');
    $('select:visible').removeAttr('novalidate');
    
    
};


function validateCategories(event) {
    var numOfAuthors = $('#author-row :checked').length;
        if (numOfAuthors === 0) {
            event.preventDefault();
            alert('Publications need at least one contributing author.');
        }

    if($('#postType').is(':checked')) {
        var categoriesChecked = $('#post-categories-display :checked').length;
        if (categoriesChecked === 0) {
            event.preventDefault();
            alert('Please select at least one category.');
        }
    } else if ($('#pageType').is(':checked')) {
        var categoryChecked = $('#page-categories-display :checked').length;

        if (categoryChecked === 0) {
            event.preventDefault();
            alert('Please select a category.');
        }
     
    }
    

};

function addHeaderChoice(imageUrl) {
    $('#header-image-choice option').each( function() {
       if ($(this).html() === imageUrl) {
           $(this).attr('selected', true);
       }
       else {
           $(this).removeAttr('selected');
       }
    });
}

function addPreviewChoice(imageUrl) {
   $('#preview-image-choice option').each( function() {
       if ($(this).html() === imageUrl) {
           $(this).attr('selected', true);
       }
       else {
           $(this).removeAttr('selected');
       }
    });
}

function addPageChoice(imageUrl) {
   $('#page-image-choice option').each( function() {
       if ($(this).html() === imageUrl) {
           $(this).attr('selected', true);
       }
       else {
           $(this).removeAttr('selected');
       }
    });
}