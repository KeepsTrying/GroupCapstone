function validateCategories(event) {
    if($('#postType').is(':checked')) {
        var categoriesChecked = $('#post-categories-display :checked').length;
        if (categoriesChecked === 0) {
            event.preventDefault();
            alert('Please select at least one category.');
        }
    } else if($('#pageType').is(':checked')) {
        var categoryChecked = $('#page-categories-display :checked').length;

        if (categoryChecked === 0) {
            event.preventDefault();
            alert('Please select a category.');
        }


        //var categoryChecked = $('#page-categories-display :checked').length;
        //if (! $('#page-categories-display:checked')) {
            
        }
    

};



function controlRequired() {
    $('input:hidden').removeAttr('required');
    $('input:hidden').attr('novalidate', 'novalidate');
    $('select:hidden').removeAttr('required');
    $('select:hidden').attr('novalidate', 'novalidate');

    $('input:visible').attr('required');
    $('input:visible').removeAttr('novalidate', 'novalidate');
    //$('input :visible').removeProp('novalidate');
    $('select:visible').attr('required');
    //$('select :visible').removeProp('novalidate');
    $('select:visible').removeAttr('novalidate', 'novalidate');
};





$('#submitButton').on('click', validateCategories);




function controlRequired() {
    $('input:hidden').removeAttr('required');
    $('input:visible').attr('novalidate', 'novalidate');
    $('select:hidden').removeAttr('required');
    $('select:visible').attr('novalidate', 'novalidate');
};